package com.github.algrithom.lb;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class WeightedRoundRobinRuleTest {
    private List<Server> listCount = new ArrayList();
    private volatile List<Server> allServerList
            = Collections.synchronizedList(new ArrayList<Server>());

    @Before
    public void init() {
        allServerList.add(new Server("192.168.1.2", 2));
        allServerList.add(new Server("192.168.1.3", 2));
        allServerList.add(new Server("192.168.1.4", 2));
        allServerList.add(new Server("192.168.1.5", 4));
        allServerList = Collections.unmodifiableList(allServerList);
    }

    @Test
    public void test() throws Exception {
        List<Server> serverWeightList = new ArrayList<>();
        for (Server server : allServerList) {
            for (int i = 0; i < server.getWeight(); i++) {
                serverWeightList.add(server);
            }
        }
        for (int i = 0; i < 100000; i++) {
            int index = incrementAndGetModulo(serverWeightList.size());
            Server chosenServer = serverWeightList.get(index);
            listCount.add(chosenServer);
        }
    }

    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    private int incrementAndGetModulo(int modulo) {
        for (; ; ) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }
    @Data
    @AllArgsConstructor
    class Server {
        private String ip;
        private Integer weight;
    }

    @After
    public void destroy() {
        Map<String, Long> collect = listCount.stream()
                .collect(Collectors.groupingBy(Server::getIp, Collectors.counting()));
        System.err.println(collect);
    }


}

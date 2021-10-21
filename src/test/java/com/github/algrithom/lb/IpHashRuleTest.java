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
import java.util.stream.Collectors;

public class IpHashRuleTest {
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
        List<String> userIpList = new ArrayList<>();
        for (Server server : allServerList) {
            userIpList.add(server.getIp());
        }
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < userIpList.size(); j++) {
                int index = userIpList.get(j).hashCode() % allServerList.size();
                Server chosenServer = allServerList.get(index);
                listCount.add(chosenServer);
            }
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

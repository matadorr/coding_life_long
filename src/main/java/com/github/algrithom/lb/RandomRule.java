package com.github.algrithom.lb;


public class RandomRule extends AbstractLoadBalanceRule {
    @Override
    public Server choose(Object key) {
        return null;
    }

    public Server choose(ILoadBalancer lb, Object key) {
        return null;
    }
}

package com.github.algrithom.lb;

public abstract class AbstractLoadBalanceRule implements IRule {
    private ILoadBalancer lb;

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}

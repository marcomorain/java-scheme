package com.marcomorain.scheme;

import com.google.common.collect.Maps;
import java.util.Map;

class RuntimeEnvironment implements Environment {

    private final Environment parent;
    private final Map<String, Cell> env;

    RuntimeEnvironment(Environment parent) {
        this.parent = parent;
        this.env = Maps.newHashMap();
    }

    @Override
    public Cell load(String symbol) throws EvaluationException {
        if (env.containsKey(symbol)) {
            return env.get(symbol);
        }
        return parent.load(symbol);
    }

    @Override
    public void save(String symbol, Cell value) {
        this.env.put(symbol, value);
    }
}

package com.wangyang.prestoDN.plugin;

import com.facebook.presto.spi.Plugin;
import com.google.common.collect.ImmutableSet;
import com.wangyang.prestoDN.window.WindowValue;

import java.util.Set;

public class MDAFunctionPlugin implements Plugin {
    @Override
    public Set<Class<?>> getFunctions() {
        return ImmutableSet.<Class<?>>builder()
                .add(WindowValue.class)
                .build()
                ;
    }
}

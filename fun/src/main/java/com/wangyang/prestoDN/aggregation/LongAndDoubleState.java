package com.wangyang.prestoDN.aggregation;

import com.facebook.presto.spi.function.AccumulatorState;

/**
 * 对于presto基于原生数据类型的聚合函数来讲
 * 需要一个继承 AccumulatorState 父状态接口的自定义接口
 * 并定义需要的以中间类型为参数或返回值的get、set
 */
public interface LongAndDoubleState extends AccumulatorState {
    long getLong();

    void setLong(long value);

    double getDouble();

    void setDouble(double value);
}

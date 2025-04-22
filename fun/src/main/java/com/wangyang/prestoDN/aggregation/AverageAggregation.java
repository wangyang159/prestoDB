package com.wangyang.prestoDN.aggregation;

import com.facebook.presto.common.block.BlockBuilder;
import com.facebook.presto.common.type.DoubleType;
import com.facebook.presto.common.type.StandardTypes;
import com.facebook.presto.spi.function.*;

/**
 * Aggregation Function，即聚合函数。计算从列中取得的值，返回一个单一的值。
 *
 * 使用一个 LongAndDoubleState 自定义接口来记录状态数据
 *
 */
@AggregationFunction(value = "avg_double_pro",alias = "avgdoublepro" )
public class AverageAggregation {

    /**
     * InputFunction 注解 input 阶段，分别在不同的 worker 中进行，将行值进行累积计算到state中
     * @param state
     * @param value
     */
    @InputFunction
    public static void input(LongAndDoubleState state, @SqlType(StandardTypes.DOUBLE) double value)
    {
        state.setLong(state.getLong() + 1);
        state.setDouble(state.getDouble() + value);
    }

    /**
     * combine阶段将上一步得到的state进行两两结合
     */
    @CombineFunction
    public static void combine(LongAndDoubleState state, LongAndDoubleState otherState)
    {
        state.setLong(state.getLong() + otherState.getLong());
        state.setDouble(state.getDouble() + otherState.getDouble());
    }


    /**
     * 最终会得到一个state，在output阶段对最终的state进行处理输出
     */
    @OutputFunction(StandardTypes.DOUBLE)
    public static void output(LongAndDoubleState state, BlockBuilder out)
    {
        long count = state.getLong();
        if (count == 0) {
            out.appendNull();
        }
        else {
            double value = state.getDouble();
            DoubleType.DOUBLE.writeDouble(out, value / count);
        }
    }

}

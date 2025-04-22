package com.wangyang.prestoDN.window;

import com.facebook.presto.common.block.BlockBuilder;
import com.facebook.presto.common.block.BlockBuilderStatus;
import com.facebook.presto.common.type.BooleanType;
import com.facebook.presto.common.type.StandardTypes;
import com.facebook.presto.common.type.VarcharType;
import com.facebook.presto.spi.function.ValueWindowFunction;
import com.facebook.presto.spi.function.WindowFunctionSignature;
import io.airlift.units.DataSize;

//@WindowFunctionSignature(name = "out_range", typeVariable = "T", returnType = "T", argumentTypes = "T")
@WindowFunctionSignature(name = "out_range", typeVariable = "T", returnType = StandardTypes.VARCHAR, argumentTypes = "T")
public class WindowValue extends ValueWindowFunction {

    public WindowValue() {
    }

    @Override
    public void reset() {
        //忽略空值
        setIgnoreNulls(true);
    }

    public void processRow(BlockBuilder output, int frameStart, int frameEnd, int currentPosition) {
        // createBlockBuilder 方法接受两个参数
        // blockBuilderStatus ：是一个状态监控对象，目的是限制BlockBuilder所含数据的太大，导致资源问题，但是一般不监控
        // expectedEntries: 是预期写入多少个数据，对于appendTo方法来将只需要一个就行
        BlockBuilder blockBuilder = BooleanType.BOOLEAN.createBlockBuilder(null, 1);
        windowIndex.appendTo(0,currentPosition,blockBuilder);
        //取出来
        boolean value = BooleanType.BOOLEAN.getBoolean(blockBuilder.build(), 0);

        VarcharType.VARCHAR.writeString(output,"当前窗口边界为："+frameStart+"--"+frameEnd+"  当前值为："+value);
    }

}

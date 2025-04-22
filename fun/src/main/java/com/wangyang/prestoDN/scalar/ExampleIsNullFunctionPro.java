package com.wangyang.prestoDN.scalar;

import com.facebook.presto.common.block.Block;
import com.facebook.presto.common.type.StandardTypes;
import com.facebook.presto.spi.function.*;
import io.airlift.slice.Slice;

/**
 * 自定义函数的进阶用法，可以将整个类视为函数体，内部写成范型的逻辑，节省了后续函数注册的开发成本
 *
 */
@ScalarFunction(value = "is_null2", alias = "isnull2",calledOnNullInput = true)
@Description("是否为空")
public final class ExampleIsNullFunctionPro {

    public ExampleIsNullFunctionPro() {
    }

    /**
     * TypeParameter：TypeParameter的使用有点类似 Java 中泛型的用法，
     *               类型变量T在声明完之后就可以在@SqlType注解中使用。
     *               在实际的调用过程中，框架会将T与实际 SQL 类型进行绑定
     *               然后再去调用以对应的原生容器类型为参数的实际方法。
     *
     *               它缺失可以被使用在方法返回类型的SqlType注解里面，但是一般不这样使用
     *               通常情况下函数需要一个明确的返回值类型
     *
     * @param value
     * @return
     */
    @TypeParameter("T")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean isNullSlice(@SqlNullable @SqlType("T") Slice value)
    {
        //String s = value.toStringUtf8();//转换成Java类操作，当然还有直接操作 字节数组的方法，但是不常用
        // return Slices.utf8Slice(s); 当返回类型是字符串的使用通常使用Slices手动转换一下Java的String
        return (value == null);
    }

    @TypeParameter("T")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean isNullLong(@SqlNullable @SqlType("T") Long value)
    {
        return (value == null);
    }

    @TypeParameter("T")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean isNullDouble(@SqlNullable @SqlType("T") Double value)
    {
        return (value == null);
    }

    @TypeParameter("T")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean isNullBoolean(@SqlNullable @SqlType("T") Boolean value)
    {
        return (value == null);
    }

    @TypeParameter("T")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean isNullBlock(@SqlNullable @SqlType("T") Block value)
    {
        return (value == null);
    }

}

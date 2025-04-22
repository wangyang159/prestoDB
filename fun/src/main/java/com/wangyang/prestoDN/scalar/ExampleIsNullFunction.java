package com.wangyang.prestoDN.scalar;

import com.facebook.presto.common.type.StandardTypes;
import com.facebook.presto.spi.function.Description;
import com.facebook.presto.spi.function.ScalarFunction;
import com.facebook.presto.spi.function.SqlNullable;
import com.facebook.presto.spi.function.SqlType;
import io.airlift.slice.Slice;

/**
 * Scalar Function，即标量函数。将传递给它的一个或者多个参数值，进行计算后，返回一个确定类型的标量值
 *
 * 开发自定义函数的时候要注意，presto的数据类型本身有很多，因此对于写函数来讲，数据类型分为两种
 * 原生容器类型和sql类型，presto会根据函数中的注解内容自动识别，并在sql执行时解析为需要的sql类型
 *
 * 原生容器类型(在代码中直接操作的类型)：boolean、long、double、Slice和Block
 *
 * Slice的本质是对byte[]进行了封装，为的是更加高效、自由地对内存进行操作
 * Block对应 SQL 中的数组类型
 *
 * SQL类型可以通过StandardTypes调用 它是一个 final 类
 * 可以通过它调用需要的类型表达 com.facebook.presto.common.type 包下有对应类型的实现presto会自己调用
 */
public class ExampleIsNullFunction {

    /**
     * ScalarFunction：用于声明标量函数的名称（value）和别名（alias）
     *                 calledOnNullInput = true 意味着当前方法允许传入Null，有null需要时必须携带
     * Description：函数说明
     * SqlType：两个作用，放在方法上用于声明函数的返回类型，放在入参数上表示参数类型
     * SqlNullable：用于表示函数的那个参数或返回结果可能为NULL。
     *             这一点就和直接执行create function语法的空处理是一样的作用
     *             如果方法的参数不使用此注解，当函数参数包含NULL时，则该函数不会被调用，框架自动返回结果NULL。
     *             当 Java 代码中用于实现函数的方法，它的返回值为包装类型时，必须要在实现方法上加上该注解，且该注解无法用于 Java 基础类型
     *
     * @param string
     * @return
     */
    @ScalarFunction(value = "is_null1", alias = "isnull1" , calledOnNullInput = true)
    @Description("判断是否为空")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean isNull(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice string)
    {
        return (string == null);
    }

}

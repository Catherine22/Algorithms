package com.catherine.stacks;

import com.catherine.stacks.impl.InfixExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author : Catherine
 */
public class InfixExpressionTest {

    @Test
    void cal() {
        InfixExpression ie = new InfixExpression();
        String exp = "( 1 + ( 2 + 3 ) * ( 4 + 5 ) ) )";
        Assertions.assertEquals(46, ie.function(exp));
    }
}

package stacks;

import com.catherine.stacks.InfixExpression;
import org.junit.jupiter.api.Assertions;

/**
 * @author : Catherine
 */
public class InfixExpressionTest {
    private InfixExpression ie = new InfixExpression();

    @org.junit.jupiter.api.Test
    void cal() {
        String exp = "( 1 + ( 2 + 3 ) * ( 4 + 5 ) ) )";
        Assertions.assertEquals(46, ie.function(exp));
    }
}

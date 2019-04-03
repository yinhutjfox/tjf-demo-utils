package tjf;

import tjf.simpleCalculator.ISimpleCalculator;
import tjf.simpleCalculator.impl.SimpleCalculatorImpl;

/**
 * @ClassName: TestMain
 * @Description:
 * @Auther: yinhutjfox
 * @Date: 2019/4/3 16:44
 */
public class TestMain {

    private static ISimpleCalculator simpleCalculator = new SimpleCalculatorImpl();

    public static void main(String[] args) {
        String exp = "1 + 2*(3.3 - 2^3 - 16 \\ 2)^2 - 2*3 + 1";
        System.out.println(simpleCalculator.calculate(exp));
    }

}

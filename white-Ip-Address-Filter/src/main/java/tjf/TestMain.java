package tjf;

import tjf.whitIpAddressFilter.IIpFilterService;
import tjf.whitIpAddressFilter.impl.IpFilterServiceImpl;

/**
 *
 * @ClassName: TestMain
 * @Description:
 * @Author: yinhutjfox
 * @Date: 2019/4/3 10:56
 */
public class TestMain {

    private final static IIpFilterService ipFilterService = new IpFilterServiceImpl();

    public static void main(String[] args) {
        String whiteIpAddress = "195.223.16.49";
        String testIpAddress = "169.12.54.16";

        ipFilterService.addWhiteIpAddress(whiteIpAddress);

        System.out.println(ipFilterService.isWhiteIpAddress(testIpAddress));
        System.out.println(ipFilterService.isWhiteIpAddress(whiteIpAddress));
    }

}

package tjf.whitIpAddressFilter.impl;

import tjf.whitIpAddressFilter.IIpFilterService;

import java.util.regex.Pattern;

/**
 *
 * @ClassName: IpFilterServiceImpl
 * @Description:
 * @Author: yinhutjfox
 * @Date: 2019/4/2 22:05
 */
public class IpFilterServiceImpl implements IIpFilterService {

    private final static Pattern pattern = Pattern.compile("^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$");

    private final static byte[][][][] whiteAddress = new byte[256][256][256][256];

    @Override
    public void addWhiteIpAddress(String ip) {
        checkIp(ip);
        int[] ips = convert(ip);
        whiteAddress[ips[0]][ips[1]][ips[2]][ips[3]] = 1;
    }

    @Override
    public boolean isWhiteIpAddress(String ip) {
        boolean whiteFlag = false;
        checkIp(ip);
        int[] ips = convert(ip);
        return 1 == whiteAddress[ips[0]][ips[1]][ips[2]][ips[3]];
    }

    private int[] convert(String ip) {
        String[] ips = ip.split("\\.");
        int[] intIps = new int[4];
        for (int i = 0; i < 4; ++i) {
            intIps[i] = Integer.parseInt(ips[i]);
        }
        return intIps;
    }

    private void checkIp(String ip) {
        if (null == ip || "".equals(ip)) {
            throw new RuntimeException("传入IP为空");
        } else {
            if (!pattern.matcher(ip).find()) {
                throw new RuntimeException("IP 地址格式有误");
            }
        }
    }
}

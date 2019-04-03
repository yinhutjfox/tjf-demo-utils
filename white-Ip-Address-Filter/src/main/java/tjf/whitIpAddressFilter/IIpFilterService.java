package tjf.whitIpAddressFilter;

/**
 *
 * @ClassName: IIpFilterService
 * @Description:
 * @Author: JingFeng.Tan
 * @Date: 2019/4/2 22:05
 */
public interface IIpFilterService {

    void addWhiteIpAddress(String ip);

    boolean isWhiteIpAddress(String ip);

}

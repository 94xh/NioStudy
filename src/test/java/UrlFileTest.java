import com.UrlToBase64Service;
import com.io.BlockingFile;

import java.util.Iterator;
import java.util.ServiceLoader;

public class UrlFileTest {
    public static final String FILE_URL = "http://xiaohaotypora.oss-cn-hangzhou.aliyuncs.com/image-20201231161022445.png";

    public static void main(String[] args) {

        // 普通的实现类
        BlockingFile blockingFile = new BlockingFile();
        blockingFile.urlToBase64Sting(FILE_URL);

        // SPI机制，寻找所有的实现类，顺序执行
        ServiceLoader<UrlToBase64Service> loader = ServiceLoader.load(UrlToBase64Service.class); // 查找SPI实现类，并加载到jvm
        Iterator<UrlToBase64Service> iter = loader.iterator();
        while (iter.hasNext()) {
            long l1 = System.currentTimeMillis();
            UrlToBase64Service spiService = iter.next();
            spiService.urlToBase64Sting(FILE_URL);
            long l2 = System.currentTimeMillis();
            System.out.println((l2 - l1));
        }
    }
}

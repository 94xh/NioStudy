import com.io.BlockingFile;
import com.nio.NioFileChannel;

public class UrlFileTest {
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            BlockingFile.urlToBase64Sting("http://xiaohaotypora.oss-cn-hangzhou.aliyuncs.com/image-20201231161022445.png");
        }
        long l2 = System.currentTimeMillis();
        System.out.println((l2 - l1));


        long l3 = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            NioFileChannel.urlToBase64Sting("http://xiaohaotypora.oss-cn-hangzhou.aliyuncs.com/image-20201231161022445.png");
        }
        long l4 = System.currentTimeMillis();
        System.out.println((l4 - l3));
    }
}

import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.factory.BeanFactory;
import com.lagou.edu.service.TransferService;
import org.junit.Test;

/**
 * @Author: zhuhf
 * @Date: 2020/4/13 5:18 上午
 */
public class BeanFactoryTest {
    @Test
    public static void main(String[] args) {
        TransferService bean = (TransferService) BeanFactory.getBean("com.lagou.edu.service.impl.TransferServiceImpl");
        System.out.println(bean);
    }
}

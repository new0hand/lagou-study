import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.factory.BeanFactory;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuhf
 * @Date: 2020/4/13 5:18 上午
 */
public class BeanFactoryTest {
    @Test
    public static void main(String[] args) {
        Map<String, Account> map = new HashMap<>();
        Account account = new Account();
        map.put("1", account);

        Account account1 = map.get("1");
        account1.setName("xxx");

        System.out.println(map);

//        TransferService bean = (TransferService) BeanFactory.getBean("com.lagou.edu.service.impl.TransferServiceImpl");
//        System.out.println(bean);
    }
}

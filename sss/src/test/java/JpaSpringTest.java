import com.lagou.edu.dao.ResumeDao;
import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: zhuhf
 * @Date: 2020/4/23 2:42 上午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JpaSpringTest {
    // 要测试IOC哪个对象注入即可
    @Autowired
//    private ResumeDao resumeDao;
    private ResumeService resumeService;

    @Test
    public void testFindAll() throws Exception {
        List<Resume> list = resumeService.queryResumeList();
        for (int i = 0; i < list.size(); i++) {
            Resume resume =  list.get(i);
            System.out.println(resume);
        }
    }
}

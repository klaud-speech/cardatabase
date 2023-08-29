package kr.pe.speech.webbiz;

import kr.pe.speech.webbiz.domain.Project;
import kr.pe.speech.webbiz.domain.ProjectRepository;
import kr.pe.speech.webbiz.domain.User;
import kr.pe.speech.webbiz.domain.UserRepository;
import kr.pe.speech.webbiz.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void relationshipTest(){
        // 테스트 데이터 생성
        Project project = new Project();
        project.setProjectname("klaud");
        project.setProjecttype("Others");

        projectRepository.save(project);

        User user = new User();
        user.setUsername("lswbhm88@abc.com");
        user.setPassword("asdf");
        user.setRole("USER");
        user.getProjects().add(project);

        userRepository.save(user);

        //테스트 코드
        //List<Project> projects = userRepository.findById(3L).get().getProjects();  // id -> uuid
        List<Project> projects = userRepository.findByUsername("lswbhm88@abc.com").get().getProjects();

        for(Project foundProject : projects ){
            System.out.println(project);
        }

        System.out.println(  userRepository.findByUsername("lswbhm88@abc.com").get()  );



    }

}

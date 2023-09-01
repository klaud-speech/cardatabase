package kr.pe.speech.webbiz;

import kr.pe.speech.webbiz.domain.Project;
import kr.pe.speech.webbiz.domain.ProjectRepository;
import kr.pe.speech.webbiz.domain.User;
import kr.pe.speech.webbiz.domain.UserRepository;
import kr.pe.speech.webbiz.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {


    private final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("User-Project Relationship")
    void relationshipTest(){
        // 테스트 데이터 생성

        User user = new User();
        user.setUsername("lswbhm88@abc.com");
        user.setPassword("asdf");
        user.setRole("USER");

        userRepository.save(user);

        Project project1 = new Project();
        project1.setProjectname("www.speech.pe.kr");
        project1.setProjecttype("Others");
        project1.setUser(user);

        projectRepository.save(project1);


        Project project2 = new Project();
        project2.setProjectname("www.llsollu.com");
        project2.setProjecttype("Others");
        project2.setUser(user);

        projectRepository.save(project2);




        //user.getProjectList().add(project1); //무시된다.

        System.out.println(  userRepository.findByUsername("lswbhm88@abc.com").get()  );



        //테스트 코드
        //List<Project> projects = userRepository.findById(3L).get().getProjects();  // id -> uuid
        List<Project> projects = userRepository.findByUsername("lswbhm88@abc.com").get().getProjectList();

        LOGGER.info( "project 개수 : " + projects.size()  );

        for(Project foundProject : projects ){
            System.out.println(foundProject);
        }
    }

}

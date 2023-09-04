package kr.pe.speech.webbiz.web;

import kr.pe.speech.webbiz.domain.Project;
import kr.pe.speech.webbiz.domain.ProjectRepository;
import kr.pe.speech.webbiz.domain.User;
import kr.pe.speech.webbiz.domain.UserRepository;
import kr.pe.speech.webbiz.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    //@RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseEntity<?> setProjectInfo (@RequestBody Map<String, Object> postData) {

        LOGGER.info( "ProjectController::setProjectInfo(@RequestBody...)");


        User user2 = new User();
        user2.setUsername("lswbhm88@abc.com");
        user2.setPassword("asdf");
        user2.setRole("USER");

        userRepository.save(user2);


        Project project1 = new Project( );
        //project1.setProjectname( projectInfo[0] );
        //project1.setProjecttype( projectInfo[1] );
        project1.setProjectname( "www.llsollu.com" );
        project1.setProjecttype( "wordpress" );
        project1.setUser(user2);
        project1.getUser().getProjectList().add(project1);
        projectRepository.save(project1);

        Project project2 = new Project( );
        project2.setProjectname( "www.speech.pe.kr" );
        project2.setProjecttype( "others" );
        project2.setUser(user2);
        project2.getUser().getProjectList().add(project2);
        projectRepository.save(project2);

        //테스트 코드

        System.out.println(  userRepository.findByUsername("lswbhm88@abc.com").get()  );

        List<Project> projects = userRepository.findByUsername("lswbhm88@abc.com").get().getProjectList();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch( InterruptedException e){
            System.err.format("IOException: %s%n", e);
        }

        LOGGER.info("projects.size() : " + projects.size() );

        for(Project  foundProject : projects ){
            LOGGER.info(" PROJECTS : " + foundProject) ;
        }


        return ResponseEntity.ok()
                .build();


    }


    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseEntity<?> setProjectInfo (@RequestBody Map<String, Object> postData, @RequestHeader("Authorization") String requestHeader ){

        final String [] projectInfo = new String[ postData.size() ];
        LOGGER.info("project 메서드가 호출되었습니다." + " argument : " + postData.size());

        String userName = jwtService.getAuthUser(requestHeader);
        LOGGER.info("project 메서드가 호출되었습니다." + " userName : " + userName );

        postData.entrySet().forEach( map ->{
            //sb.append(map.getKey() + " : " + map.getValue() + "\n");

            LOGGER.info("temp:" +  map.getValue().toString()  );

            String temp = map.getValue().toString();
            if( (map.getKey()).equals("projectName") ) {
                projectInfo[0] =  temp;
            }
            if( (map.getKey()).equals("projectType") ) projectInfo[1] = temp;
        });

        if( projectInfo[0].length() > 0) {
            Optional<Project> projectFound =  projectRepository.findByProjectname(projectInfo[0]);
            if (  projectFound.isEmpty() ) {


                //TODO
                // 현재 Login 된 것으로 바꾸어야 함. ( 3L -> ?? )
                Optional<User> user = userRepository.findByUsername(userName);
                if( user.isPresent()) {

                    //다대일
                    User user1 = user.get();

                    Project project1 = new Project( );
                    project1.setProjectname( projectInfo[0] );
                    project1.setProjecttype( projectInfo[1] );
                    project1.setUser(user1);
                    project1.getUser().getProjectList().add(project1);
                    projectRepository.save(project1);


                    /*
                    LOGGER.info("user1 : " + user1 );
                    LOGGER.info("user1.getUserName() : " + user1.getUsername() );
                    LOGGER.info("userRepository.findByUsername(user1.getUsername()).get() : " + userRepository.findByUsername(user1.getUsername()).get() );
                     */


                    //테스트 코드
                    List<Project> projects = userRepository.findByUsername(user1.getUsername()).get().getProjectList();
                    //List<Project> projects = userRepository.findByUsername("lswbhm88@abc.com").get().getProjectList();

                    LOGGER.info("projects.size() : " + projects.size() );

                    for(Project  foundProject : projects ){
                        LOGGER.info(" PROJECTS : " + foundProject) ;
                    }


                    /* 일대다
                    projectRepository.save(project);   //DB저장...

                    LOGGER.info( "user : " + user );
                    LOGGER.info( "project : " + project );
                    user.get().getProjects().add(project); ///////

                    LOGGER.info( "user >: " + user );
                    LOGGER.info( "project >: " + project );

                    //테스트 코드
                    List<Project> projects = userRepository.findById(2L).get().getProjects();

                    for(Project  foundProject : projects ){
                        LOGGER.info(" PROJECTS : " + foundProject) ;
                    }
                    */


                }

                return ResponseEntity.ok()
                        .build();
            }
            else{
                return ResponseEntity.badRequest()
                        .build();
            }
        }
        else {
            return ResponseEntity.badRequest()
                    .build();
        }
    }

    @RequestMapping(value = "/url", method = RequestMethod.POST)
    public ResponseEntity<?> setup (@RequestBody Map<String, Object> postData, @RequestHeader("Authorization") String requestHeader) {

        final String[] projectInfo = new String[postData.size()];
        LOGGER.info("url 메서드가 호출되었습니다." + " argument : " + postData.size());

        String userName = jwtService.getAuthUser(requestHeader);
        LOGGER.info("project 메서드가 호출되었습니다." + " userName : " + userName );

        postData.entrySet().forEach(map -> {
            LOGGER.info("temp:" +  map.getValue().toString()  );

            if ((map.getKey()).equals("domainurl")) {
                projectInfo[0] = map.getValue().toString();
            }
            if ((map.getKey()).equals("sourcelanguage"))
                projectInfo[1] = map.getValue().toString();
            if ((map.getKey()).equals("targetlanguage")){
                projectInfo[1] = map.getValue().toString();
            }
            if ((map.getKey()).equals("urltype"))
                projectInfo[3] = map.getValue().toString();



        });

        return ResponseEntity.ok()
                .build();
    }
}

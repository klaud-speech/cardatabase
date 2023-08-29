package kr.pe.speech.webbiz.web;

import kr.pe.speech.webbiz.domain.Project;
import kr.pe.speech.webbiz.domain.ProjectRepository;
import kr.pe.speech.webbiz.domain.User;
import kr.pe.speech.webbiz.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseEntity<?> setProjectInfo (@RequestBody Map<String, Object> postData){

        final String [] projectInfo = new String[ postData.size() ];

        LOGGER.info("project 메서드가 호출되었습니다." + " argument : " + postData.size());

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
                Project project = new Project( projectInfo[0], projectInfo[1] );

                projectRepository.save(project);   //DB저장...

                //TODO
                // 현재 Login 된 것으로 바꾸어야 함. ( 3L -> ?? )
                //User user = userRepository
                //user.getProjects().add(project);

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
    public ResponseEntity<?> setup (@RequestBody Map<String, Object> postData) {

        final String[] projectInfo = new String[postData.size()];

        LOGGER.info("url 메서드가 호출되었습니다." + " argument : " + postData.size());

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

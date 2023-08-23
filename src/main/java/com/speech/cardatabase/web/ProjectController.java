package com.speech.cardatabase.web;

import com.speech.cardatabase.domain.Owner;
import com.speech.cardatabase.domain.Project;
import com.speech.cardatabase.domain.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseEntity<?> setProjectInfo (@RequestBody Map<String, Object> postData){

        final String [] projectInfo = new String[ postData.size() ];

        postData.entrySet().forEach( map ->{
            //sb.append(map.getKey() + " : " + map.getValue() + "\n");
            String temp = map.getValue().toString();
            if( (map.getKey()).equals("projectName") ) {
                projectInfo[0] =  temp;
            }
            if( (map.getKey()).equals("projectType") ) projectInfo[1] = temp;
        });

        if( projectInfo[0].length() > 0) {
            Optional<Project> projectFound =  projectRepository.findByProjectname(projectInfo[0]);
            if (  projectFound.isEmpty() ) {
                projectRepository.save(new Project(projectInfo[0], projectInfo[1]));   //DB저장...
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

        LOGGER.info("url 메서드가 호출되었습니다.");

        /*
        postData.entrySet().forEach(map -> {
            //sb.append(map.getKey() + " : " + map.getValue() + "\n");
            String temp = map.getValue().toString();
            if ((map.getKey()).equals("domainurl")) {
                projectInfo[0] = temp;
            }
            if ((map.getKey()).equals("sourcelanguage"))
                projectInfo[1] = temp;

            if ((map.getKey()).equals("urltype"))
                projectInfo[3] = temp;

        });
        */
        return ResponseEntity.ok()
                .build();
    }
}

package com.speech.cardatabase.web;

import com.speech.cardatabase.domain.Owner;
import com.speech.cardatabase.domain.Project;
import com.speech.cardatabase.domain.ProjectRepository;
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

    @RequestMapping(value = "/setProjectInfo", method = RequestMethod.POST)
    public ResponseEntity<?> setProjectInfo (@RequestBody Map<String, Object> postData){

        final String [] projectInfo = new String[ postData.size() ];

        postData.entrySet().forEach( map ->{
            //sb.append(map.getKey() + " : " + map.getValue() + "\n");
            String temp = map.getValue().toString();
            if( (map.getKey()).equals("projectname") ) {
                projectInfo[0] =  temp;
            }
            if( (map.getKey()).equals("projecttype") ) projectInfo[1] = temp;
        });

        Optional<Project> projectFound =  projectRepository.findByProjectname(projectInfo[0]);

        if( projectFound.isEmpty() ) {
            if (projectInfo[0].length() > 0)
                projectRepository.save(new Project(projectInfo[0], projectInfo[1]));
            return ResponseEntity.ok()
                    .build();
        }
        else {
            return ResponseEntity.badRequest()
                    .build();
        }
    }
}

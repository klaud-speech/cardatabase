package kr.pe.speech.webbiz;

import kr.pe.speech.webbiz.domain.Project;
import kr.pe.speech.webbiz.domain.ProjectRepository;
import kr.pe.speech.webbiz.domain.User;
import kr.pe.speech.webbiz.domain.UserRepository;
import kr.pe.speech.webbiz.service.JwtService;
import kr.pe.speech.webbiz.web.ProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, java.io.IOException {

        //LOGGER.info( "AuthenticationFilter::doFilterInternal(...)");

        // Authorization 헤더에서 토큰을 가져옴
        String jws = request.getHeader(HttpHeaders.AUTHORIZATION);
        //LOGGER.info( "[JWT] token:" + jws );
        //LOGGER.info( "[JWT] request:" + request );
        if( jws != null ){
            //토큰을 확인하고 사용자를 얻음.
            String user = jwtService.getAuthUser(request);
            LOGGER.info( "[JWT] user:" + user );   //username

            //인증
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(user, null, java.util.Collections.emptyList() );

            //LOGGER.info( "[JWT] authentication:" + authentication );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }
        else{
            LOGGER.error( "[JWT] HttpHeaders.AUTHORIZATION is NULL" );
            //throw new RuntimeException("권한정보가 없는 토큰입니다.");
       }

        //LOGGER.info("Next Filter Chain");



        filterChain.doFilter( request, response);


        //LOGGER.info("After Filter Chain");
    }
}

package com.example.libdev.dynamicauthorization;

import com.example.libdev.domain.VEndpoint;
import com.example.libdev.repository.VEndpointRepository;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DynamicAuthorizationUtils {

    private final VEndpointRepository vEndpointRepository;

    public DynamicAuthorizationUtils(VEndpointRepository vEndpointRepository) {
        this.vEndpointRepository = vEndpointRepository;
    }

    public HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    public String getPath(HttpServletRequest request){
        return request.getRequestURI().substring(request.getContextPath().length());
    }

    public boolean isRequestDynamicAuthorizationEnabled(HttpServletRequest request){
        return vEndpointRepository.findByMethodAndUrl(request.getMethod().toUpperCase(), getPath(request)).isPresent();
    }

    public boolean isRequestDynamicAuthorizationEnabled(){
        HttpServletRequest request = getRequest();
        return isRequestDynamicAuthorizationEnabled(request);
    }

    public Optional<VEndpoint> getEndpoint(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return vEndpointRepository.findByMethodAndUrl(request.getMethod().toUpperCase(), getPath(request));
    }

//    public String getRequestBody(){
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        try {
//            BufferedReader inputStream = request.getReader();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            return .lines().collect(Collectors.joining(System.lineSeparator()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

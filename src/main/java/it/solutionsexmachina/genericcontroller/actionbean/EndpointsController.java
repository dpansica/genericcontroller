package it.solutionsexmachina.genericcontroller.actionbean;

import it.solutionsexmachina.genericcontroller.annotation.ServiceMethod;
import it.solutionsexmachina.genericcontroller.dto.MethodDTO;
import org.reflections.Reflections;

import javax.inject.Named;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named("Endpoints")
public class EndpointsController extends GenericActionBean{

    @ServiceMethod("getAllEndpoints")
    public List<MethodDTO> getAllEndpoints(MethodDTO filter)
    {
        List<MethodDTO> result = new ArrayList<>();
        Set<Class<?>> classes = new Reflections("it.solutionsexmachina").getTypesAnnotatedWith(Named.class);

        for (Class clazz : classes) {
            for (Method method : clazz.getMethods()) {
                if (method.getAnnotation(ServiceMethod.class)!=null){
                    MethodDTO methodDTO = new MethodDTO();
                    methodDTO.setName(clazz.getName()+"."+method.getName());
                    result.add(methodDTO);
                }
            }
        }
        return result;
    }
}

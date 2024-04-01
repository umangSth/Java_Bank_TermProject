//package Bank_Term_Project.Bank_TP;
//
///**
// * Hello world!
// *
// */
//public class App 
//{
//    public static void main( String[] args )
//    {
//        System.out.println( "Hello World!" );
//    }
//}
package Bank_Term_Project.Bank_TP;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import Bank_Term_Project.Bank_TP.config.WebConfig;

public class App extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null; // No root configuration classes
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class }; // Configuration class for Spring MVC
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // Map DispatcherServlet to "/"
    }
    
 
}
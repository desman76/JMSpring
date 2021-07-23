import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld bean2 =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());
        System.out.println(bean == bean2);

        Cat catBean = applicationContext.getBean("cat", Cat.class);
        Cat catBean2 = applicationContext.getBean("cat", Cat.class);
        System.out.println(catBean == catBean2);
    }
}
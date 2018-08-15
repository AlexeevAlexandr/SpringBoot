import org.springframework.boot.SpringApplication;

class Application{
    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[] {Application.class, JpaConfig.class}, args);
    }

}


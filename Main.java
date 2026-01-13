import controller.HungerGamesController;
import repository.HungerGamesRepository;
import service.HungerGamesService;

public class Main {
    public static void main(String[] args) {

        HungerGamesRepository repository = new HungerGamesRepository();
        HungerGamesService service = new HungerGamesService(repository);
        HungerGamesController controller = new HungerGamesController(service);


        controller.run();
    }
}
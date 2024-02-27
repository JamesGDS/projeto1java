import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EventManager {
    private static final String EVENT_FILE = "events.data";

    private List<User> users;
    private List<Event> events;

    public EventManager() {
        this.users = new ArrayList<>();
        this.events = new ArrayList<>();
        loadEventsFromFile();
    }

    public void registerUser(String name, String email, String city) {
        User user = new User(name, email, city);
        users.add(user);
    }

    public void createEvent(String name, String address, String category, LocalDateTime dateTime, String description) {
        Event event = new Event(name, address, category, dateTime, description);
        events.add(event);
        saveEventsToFile();
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getUpcomingEvents() {
        // Implement logic to filter upcoming events
        return null;
    }

    public List<Event> getPastEvents() {
        // Implement logic to filter past events
        return null;
    }

    private void loadEventsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EVENT_FILE))) {
            events = (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar eventos do arquivo: " + e.getMessage());
        }
    }

    private void saveEventsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EVENT_FILE))) {
            oos.writeObject(events);
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos no arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("**Menu de Eventos**");
            System.out.println("1. Registrar Usuário");
            System.out.println("2. Criar Evento");
            System.out.println("3. Listar Todos os Eventos");
            System.out.println("4. Listar Próximos Eventos");
            System.out.println("5. Listar Eventos Passados");
            System.out.println("6. Sair");

            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Digite o nome do usuário: ");
                    String name = scanner.nextLine();
                    System.out.print("Digite o email do usuário: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite a cidade do usuário: ");
                    String city = scanner.nextLine();
                    eventManager.registerUser(name, email, city);
                    System.out.println("Usuário registrado com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o nome do evento: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Digite o endereço do evento: ");
                    String eventAddress = scanner.nextLine();
                    System.out.print("Digite a categoria do evento: ");
                    String eventCategory = scanner.nextLine();
                    System.out.print("Digite a data e hora do evento (formato yyyy-MM-dd HH:mm): ");
                    String eventDateTimeStr = scanner.nextLine();
                    LocalDateTime eventDateTime = LocalDateTime.parse(eventDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    System.out.print("Digite a descrição do evento: ");
                    String eventDescription = scanner.nextLine();
                    eventManager.createEvent(eventName, eventAddress, eventCategory, eventDateTime, eventDescription);
                    System.out.println("Evento criado com sucesso!");
                    break;
                case 3:
                    System.out.println("**Lista de Todos os Eventos**");
                    for (Event event : eventManager.getEvents()) {
                        System.out.println(event);
                    }
                    break;
                case 4:
                    // Implementar listagem de próximos eventos
                    break;
                case 5:
                    // Implementar listagem de eventos passados
                    break;
                case 6:
                    System.out.println("Saindo do programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }
}

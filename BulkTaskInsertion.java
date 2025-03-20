import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BulkTaskInsertion {

    private static final String[] TASK_NAMES = {
            "Comprar pão", "Ir à academia", "Estudar Java", "Revisar código", "Escrever relatório",
            "Preparar café", "Fazer compras", "Organizar a mesa", "Agendar reunião", "Lavar o carro"
    };

    private static final String[] DESCRIPTIONS = {
            "Tarefa simples do dia", "Precisa de mais atenção", "Prioridade alta",
            "Pode ser feito mais tarde", "Importante concluir até o fim da semana"
    };

    public static void main(String[] args) {
        String url = "http://localhost:8081/tasks";
        String token = "";
        Random random = new Random();

        for (int i = 1; i <= 100; i++) {
            String taskName = TASK_NAMES[random.nextInt(TASK_NAMES.length)] + " #" + i;
            String description = DESCRIPTIONS[random.nextInt(DESCRIPTIONS.length)];
            LocalDateTime dateExecuted = LocalDateTime.now().plusDays(random.nextInt(30) + 1);
            String formattedDate = dateExecuted.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            String payload = String.format("{\"taskName\":\"%s\",\"description\":\"%s\",\"dateExecuted\":\"%s\"}",
                    taskName, description, formattedDate);

            try {
                int responseCode = getResponseCode(url, token, payload);
                System.out.println("Tarefa " + i + " - Status: " + responseCode);
            } catch (Exception e) {
                System.err.println("Erro ao enviar tarefa " + i + ": " + e.getMessage());
            }
        }
    }

    private static int getResponseCode(String url, String token, String payload) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "insomnia/10.3.1");
        connection.setRequestProperty("Authorization", token);
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = payload.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        return connection.getResponseCode();
    }
}

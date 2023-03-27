import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var cliente = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
        String body = response.body();
        

        // pegar só os dados que interessa(titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
    
        // exibir e manipular dos dados
        for (Map<String,String> filmes : listaDeFilmes) {
            System.out.println("Titulo: " + "\u001b[1m" + filmes.get("title") + "\u001b[m:");
            System.out.println("\u001b[37m\u001b[45m Nota do filme: "+ "\u001b[1m" + filmes.get("imDbRating") + "\u001b[m");
            double classificação = Double.parseDouble(filmes.get("imDbRating"));
            int estrelas = (int) classificação;
            for(int i = 0; i <= estrelas; i++) {
                System.out.print("\u2B50");
            }
            System.out.println();
            System.out.println("Capa do filme: " + "\u001b[1m" + filmes.get("image") + "\u001b[m ");
            
            
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            
        }
    }
}
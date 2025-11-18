import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class main {

    private static final String API_KEY = "b143e3df9c6eedada0cd191a";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Conversor de Moedas ===");
        System.out.println("1. USD → BRL");
        System.out.println("2. BRL → USD");
        System.out.println("3. USD → EUR");
        System.out.println("4. EUR → USD");
        System.out.println("5. BRL → EUR");
        System.out.println("6. EUR → BRL");
        System.out.println("7. USD → JPY");
        System.out.println("8. JPY → USD");
        System.out.println("9. USD → GBP");
        System.out.println("10. GBP → USD");
        System.out.println("11. USD → CAD");
        System.out.println("12. CAD → USD");

        System.out.print("\nEscolha uma opção (1-12): ");
        int opcao = scanner.nextInt();

        System.out.print("Valor: ");
        double valor = scanner.nextDouble();

        String origem = obterMoedaOrigem(opcao);
        String destino = obterMoedaDestino(opcao);

        if (origem == null || destino == null) {
            System.out.println("Opção inválida!");
            return;
        }

        double taxa = buscarTaxa(origem, destino);

        if (taxa < 0) {
            System.out.println("Erro ao buscar taxa.");
            return;
        }

        double convertido = valor * taxa;

        System.out.printf("Resultado: %.2f %s → %.2f %s\n", valor, origem, convertido, destino);
    }

    public static String obterMoedaOrigem(int op) {
        switch (op) {
            case 1: return "USD";
            case 2: return "BRL";
            case 3: return "USD";
            case 4: return "EUR";
            case 5: return "BRL";
            case 6: return "EUR";
            case 7: return "USD";
            case 8: return "JPY";
            case 9: return "USD";
            case 10: return "GBP";
            case 11: return "USD";
            case 12: return "CAD";
            default: return null;
        }
    }

    public static String obterMoedaDestino(int op) {
        switch (op) {
            case 1: return "BRL";
            case 2: return "USD";
            case 3: return "EUR";
            case 4: return "USD";
            case 5: return "EUR";
            case 6: return "BRL";
            case 7: return "JPY";
            case 8: return "USD";
            case 9: return "GBP";
            case 10: return "USD";
            case 11: return "CAD";
            case 12: return "USD";
            default: return null;
        }
    }

    public static double buscarTaxa(String origem, String destino) {
        try {
            URL url = new URL(API_URL + origem);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = br.readLine()) != null) {
                resposta.append(linha);
            }
            br.close();

            String json = resposta.toString();

            String chave = "\"" + destino + "\":";
            int index = json.indexOf(chave);

            if (index == -1) return -1;

            int inicio = index + chave.length();
            int fim = json.indexOf(",", inicio);

            if (fim == -1) {
                fim = json.indexOf("}", inicio);
            }

            String valorStr = json.substring(inicio, fim);

            return Double.parseDouble(valorStr);

        } catch (Exception e) {
            return -1;
        }
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {

        try {

            ServerSocket tcpServer = new ServerSocket(5055);

            System.out.println("TCP server je aktivan!");
            System.out.println("Server trenutno ceka klijenta...\n");

            Socket clientConnection = tcpServer.accept();

            System.out.println("Klijent je uspjesno povezan.\n");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientConnection.getInputStream()));

            PrintWriter writer = new PrintWriter(
                    clientConnection.getOutputStream(), true);

            int brojPoruka = 0;

            while (brojPoruka < 5) {

                String clientText = reader.readLine();

                brojPoruka++;

                System.out.println("Poruka " + brojPoruka + ": " + clientText);

                writer.println("Server je obradio poruku broj " + brojPoruka);
            }

            System.out.println("\nTCP komunikacija je zavrsena.");
            System.out.println("Ukupno obradjenih poruka: " + brojPoruka);

            reader.close();
            writer.close();
            clientConnection.close();
            tcpServer.close();

        } catch (Exception greska) {

            System.out.println("Pojavila se greska tokom rada servera.");
            System.out.println(greska.getMessage());
        }
    }
}
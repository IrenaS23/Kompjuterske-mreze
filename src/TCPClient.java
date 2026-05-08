import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) {

        try {

            Socket connection = new Socket("localhost", 5055);

            System.out.println("TCP klijent je povezan sa serverom.\n");

            BufferedReader responseReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            PrintWriter sender = new PrintWriter(
                    connection.getOutputStream(), true);

            for (int broj = 1; broj <= 5; broj++) {

                String tekstPoruke =
                        "Poruka od klijenta broj " + broj;

                sender.println(tekstPoruke);

                System.out.println("Poslato: " + tekstPoruke);

                String odgovor = responseReader.readLine();

                System.out.println("Odgovor servera: " + odgovor);

                System.out.println();
            }

            System.out.println("\nTCP klijent zavrsava komunikaciju.");

            sender.close();
            responseReader.close();
            connection.close();

        } catch (Exception greska) {

            System.out.println("Greska kod TCP klijenta.");
            System.out.println(greska.getMessage());
        }
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer100pr {

    public static void main(String[] args) {

        try {

            ServerSocket tcpServer =
                    new ServerSocket(5055);

            System.out.println("TCP server za 100 poruka je pokrenut.");
            System.out.println("Cekanje povezivanja klijenta...\n");

            Socket konekcija = tcpServer.accept();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    konekcija.getInputStream()
                            )
                    );

            PrintWriter writer =
                    new PrintWriter(
                            konekcija.getOutputStream(),
                            true
                    );

            int brojPrimljenih = 0;

            long pocetak = System.currentTimeMillis();

            while (brojPrimljenih < 100) {

                String poruka = reader.readLine();

                brojPrimljenih++;

                System.out.println(
                        "TCP primljena poruka broj "
                                + brojPrimljenih
                                + ": "
                                + poruka
                );

                writer.println(
                        "Server je registrovao poruku "
                                + brojPrimljenih
                );
            }

            long kraj = System.currentTimeMillis();

            System.out.println("\nTCP eksperiment zavrsen.");
            System.out.println(
                    "Ukupno primljenih poruka: "
                            + brojPrimljenih
            );

            System.out.println(
                    "Vrijeme trajanja: "
                            + (kraj - pocetak)
                            + " ms"
            );

            reader.close();
            writer.close();
            konekcija.close();
            tcpServer.close();

        } catch (Exception e) {

            System.out.println("Greska kod TCP servera.");
            System.out.println(e.getMessage());
        }
    }
}
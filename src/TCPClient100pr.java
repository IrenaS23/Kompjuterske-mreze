import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient100pr {

    public static void main(String[] args) {

        try {

            Socket tcpKlijent =
                    new Socket("localhost", 5055);

            BufferedReader odgovorServera =
                    new BufferedReader(
                            new InputStreamReader(
                                    tcpKlijent.getInputStream()
                            )
                    );

            PrintWriter sender =
                    new PrintWriter(
                            tcpKlijent.getOutputStream(),
                            true
                    );

            int brojPoslatih = 0;

            long pocetak = System.currentTimeMillis();

            while (brojPoslatih < 100) {

                brojPoslatih++;

                String poruka =
                        "TCP test poruka broj "
                                + brojPoslatih;

                sender.println(poruka);

                String odgovor =
                        odgovorServera.readLine();

                System.out.println(
                        "Poslato: " + poruka
                );

                System.out.println(
                        "Odgovor servera: "
                                + odgovor
                );

                Thread.sleep(20);
            }

            long kraj = System.currentTimeMillis();

            System.out.println("\nTCP klijent zavrsio slanje.");
            System.out.println(
                    "Ukupno poslatih poruka: "
                            + brojPoslatih
            );

            System.out.println(
                    "Ukupno vrijeme: "
                            + (kraj - pocetak)
                            + " ms"
            );

            sender.close();
            odgovorServera.close();
            tcpKlijent.close();

        } catch (Exception e) {

            System.out.println("Greska kod TCP klijenta.");
            System.out.println(e.getMessage());
        }
    }
}
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String[] args) {

        try {

            DatagramSocket klijent = new DatagramSocket();

            InetAddress adresaServera =
                    InetAddress.getByName("localhost");

            int brojPoslatihPoruka = 1;

            System.out.println("UDP klijent je pokrenut.\n");

            while (brojPoslatihPoruka <= 5) {

                String tekst =
                        "UDP test poruka broj "
                                + brojPoslatihPoruka;

                byte[] podaciZaSlanje = tekst.getBytes();

                DatagramPacket paket =
                        new DatagramPacket(
                                podaciZaSlanje,
                                podaciZaSlanje.length,
                                adresaServera,
                                7077
                        );

                klijent.send(paket);

                System.out.println(
                        "Poslata UDP poruka  "
                                + brojPoslatihPoruka
                );

                System.out.println(
                        "Sadrzaj poruke: "
                                + tekst
                                + "\n"
                );

                brojPoslatihPoruka++;

                Thread.sleep(600);
            }

            System.out.println("UDP klijent zavrsava komunikaciju.");

            klijent.close();

        } catch (Exception e) {

            System.out.println("Greska kod UDP klijenta.");
            System.out.println(e.getMessage());
        }
    }
}
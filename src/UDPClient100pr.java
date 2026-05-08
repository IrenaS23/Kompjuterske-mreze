import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient100pr {

    public static void main(String[] args) {

        try {

            DatagramSocket udpKlijent =
                    new DatagramSocket();

            InetAddress serverAdresa =
                    InetAddress.getByName("localhost");

            int poslatoUDP = 0;

            long pocetak = System.currentTimeMillis();

            System.out.println("UDP klijent salje 100 poruka.\n");

            while (poslatoUDP < 100) {

                poslatoUDP++;

                String tekstPoruke =
                        "UDP eksperiment poruka broj "
                                + poslatoUDP;

                byte[] podaci =
                        tekstPoruke.getBytes();

                DatagramPacket paket =
                        new DatagramPacket(
                                podaci,
                                podaci.length,
                                serverAdresa,
                                7077
                        );

                udpKlijent.send(paket);

                System.out.println(
                        "Poslata UDP poruka broj "
                                + poslatoUDP
                );

                Thread.sleep(15);
            }

            long kraj = System.currentTimeMillis();

            System.out.println("\nUDP slanje je zavrseno.");

            System.out.println(
                    "Ukupno poslatih UDP poruka: "
                            + poslatoUDP
            );

            System.out.println(
                    "Ukupno vrijeme: "
                            + (kraj - pocetak)
                            + " ms"
            );

            udpKlijent.close();

        } catch (Exception e) {

            System.out.println("Greska kod UDP klijenta.");
            System.out.println(e.getMessage());
        }
    }
}
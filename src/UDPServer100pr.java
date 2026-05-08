import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer100pr {

    public static void main(String[] args) {

        try {

            DatagramSocket udpServer =
                    new DatagramSocket(7077);

            int udpBrojac = 0;

            long pocetak = System.currentTimeMillis();

            System.out.println("UDP server za 100 poruka je aktivan.");
            System.out.println("Cekanje UDP paketa...\n");

            while (udpBrojac < 100) {

                byte[] buffer = new byte[1024];

                DatagramPacket paket =
                        new DatagramPacket(
                                buffer,
                                buffer.length
                        );

                udpServer.receive(paket);

                udpBrojac++;

                String tekst =
                        new String(
                                paket.getData(),
                                0,
                                paket.getLength()
                        );

                System.out.println(
                        "Primljena UDP poruka broj "
                                + udpBrojac
                                + ": "
                                + tekst
                );
            }

            long kraj = System.currentTimeMillis();

            System.out.println("\nUDP eksperiment je zavrsen.");

            System.out.println(
                    "Ukupan broj UDP poruka: "
                            + udpBrojac
            );

            System.out.println(
                    "Trajanje komunikacije: "
                            + (kraj - pocetak)
                            + " ms"
            );

            udpServer.close();

        } catch (Exception e) {

            System.out.println("Problem kod UDP servera.");
            System.out.println(e.getMessage());
        }
    }
}
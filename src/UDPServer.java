import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String[] args) {

        try {

            DatagramSocket server = new DatagramSocket(7077);

            int brojUDPporuka = 0;

            System.out.println("UDP server je aktiviran.");
            System.out.println("Server osluskuje port 7077...\n");

            while (brojUDPporuka < 5) {

                byte[] podaci = new byte[1024];

                DatagramPacket udpPaket =
                        new DatagramPacket(podaci, podaci.length);

                server.receive(udpPaket);

                brojUDPporuka++;

                String sadrzajPoruke = new String(
                        udpPaket.getData(),
                        0,
                        udpPaket.getLength()
                );

                System.out.println("Primljena UDP poruka  " + brojUDPporuka);
                System.out.println("Sadrzaj: " + sadrzajPoruke);

                System.out.println(
                        "Posiljalac: "
                                + udpPaket.getAddress()
                                + " | port: "
                                + udpPaket.getPort()
                );

                System.out.println("Cekanje naredne UDP poruke...\n");
            }

            System.out.println("UDP komunikacija je zavrsena.");
            System.out.println("Ukupno primljenih UDP poruka: "
                    + brojUDPporuka);

            server.close();

        } catch (Exception e) {

            System.out.println("Greska tokom UDP komunikacije.");
            System.out.println(e.getMessage());
        }
    }
}
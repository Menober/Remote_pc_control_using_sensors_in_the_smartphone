# Remote_pc_control_using_sensors_in_the_smartphone
Remote computer control using sensors in the phone


PC-JAVA-SERVER-CODE:

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Serwer {
    public static int x=0,y=0;
    public static boolean pressed=false,isPressed=false;

    public static void main(String []args) throws IOException, AWTException {
        Robot robot= null;
        long lastChange=0;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        DatagramSocket serverSocket = new DatagramSocket(1234);
        byte[] receiveData=new byte[1024];
        byte[] sendData = new byte[1024];


        while(true){
            for(int i=0;i<receiveData.length;i++)
                receiveData[i]=0;

            DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());
            InetAddress inetAddress=receivePacket.getAddress();
            int port = receivePacket.getPort();

            sentence=sentence.trim();
            System.out.println(sentence);
            String [] data=sentence.toLowerCase().split("\\|");
            try {
                y= Integer.valueOf(data[0]);
                x= Integer.valueOf(data[1]);
                if(data[2].equals("false"))
                    pressed=false;
                else
                    pressed=true;
            }catch(NumberFormatException e){}

            if(System.currentTimeMillis()-lastChange>1) {
                if(pressed&&!isPressed){
                robot.mousePress(InputEvent.BUTTON1_MASK);
                isPressed=true;
                }
                else if(!pressed){
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    isPressed=false;
                }


                if (Serwer.y > 1 || Serwer.y < -1)
                    robot.mouseMove((int) MouseInfo.getPointerInfo().getLocation().getX(), (int) MouseInfo.getPointerInfo().getLocation().getY() - y / 4);
                if (Serwer.x > 1 || Serwer.x < -1)
                    robot.mouseMove((int) MouseInfo.getPointerInfo().getLocation().getX() - x / 4, (int) MouseInfo.getPointerInfo().getLocation().getY());
                lastChange=System.currentTimeMillis();
            }

        }

    }


}

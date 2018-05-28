package com.example.krzysztof.tcpphonemouse;

import com.example.krzysztof.tcpphonemouse.MainActivity;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Krzysztof on 28.03.2018.
 */

public class TCP implements Runnable {


    public TCP(){
        ;
    }

    @Override
    public void run() {



        System.out.println("Ruszam TCP");
        try{

            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(MainActivity.IP);
            String sentence;

            DatagramPacket sendPacket;

            while(true) {

                    sentence=String.valueOf((int)MainActivity.orientations[1]+"|"+(int)MainActivity.orientations[2])+"|"+MainActivity.pressed;
                    sendPacket= new DatagramPacket(sentence.getBytes(), sentence.getBytes().length, IPAddress, MainActivity.PORT);
                    clientSocket.send(sendPacket);


            }
//            clientSocket.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

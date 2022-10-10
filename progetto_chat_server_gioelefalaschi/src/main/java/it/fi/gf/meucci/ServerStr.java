package it.fi.gf.meucci;
import java.io.*;
import java.net.*;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerStr(){
        try{
            server = new ServerSocket(7777);
            System.out.println("Server inizio esecuzione");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Socket attendi(){
        try{
            System.out.println("SERVER partito in esecuzione...");
            client = server.accept();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try{
            System.out.println("Benvenuto client, scrivo una frase e io la trasformo in maiuscolo. Attendo...");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("Ricevuta la stringa dal client : "+ stringaRicevuta);
            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("Invio la stringa modificata al client ...");
            outVersoClient.writeBytes(stringaModificata+'\n');
            System.out.println("SERVER: fine elaborazione ... buona notte");
            client.close();
        }
        catch(Exception e){
        }
    }
}

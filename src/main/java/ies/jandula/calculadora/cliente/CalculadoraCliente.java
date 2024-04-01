package ies.jandula.calculadora.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import ies.jandula.calculadora.servidor.CalculadoraServidor;

public class CalculadoraCliente 
{
	private static final String HOST ="localhost";
	
	public static void main(String[] args) 
	{
		int numero1;
		int numero2;		
		String simbolo;
		
		Scanner scanner = null;
		Scanner scanner2 = null;
        Socket socketCliente = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
                
        try 
        {
        	scanner = new Scanner (System.in);
        	scanner2 = new Scanner (System.in);
        	
			socketCliente = new Socket(HOST, CalculadoraServidor.PUERTO);
			
			System.out.println("Introduce un numero");
			numero1 = scanner.nextInt();
			
			System.out.println("Introduce otro numero");
			numero2 = scanner.nextInt();
			
			System.out.println("Introduce un simbolo");
			simbolo = scanner2.nextLine();
			
			dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());    
	        dataOutputStream.writeUTF(String.valueOf(numero1));
	        dataOutputStream.writeUTF(String.valueOf(numero2));
	        dataOutputStream.writeUTF(simbolo);
	        
	        dataInputStream = new DataInputStream(socketCliente.getInputStream());
			System.out.println(dataInputStream.readUTF());
        } 
        catch (UnknownHostException unknownHostException) 
        {
            unknownHostException.printStackTrace();
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally 
        {
        	try 
            {
                if (dataOutputStream != null)
                {
                    dataOutputStream.close();
                }
                if (dataInputStream != null)
                {
                    dataInputStream.close();
                }
                if (socketCliente != null)
                {
                    socketCliente.close();
                }
                if (scanner != null)
                {
                    scanner.close();
                }
            } 
            catch (IOException ioException) 
            {
                ioException.printStackTrace();
            }
		}
		
		
	}

}

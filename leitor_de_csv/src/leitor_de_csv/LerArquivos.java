package leitor_de_csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;


public class LerArquivos {
	
	
	
	public static void deletaArquivo(File file ) {
		
		if(file != null) {
			 if (file.delete()) {   
		        	System.out.println("Deleted the file: " + file.getName());     
		        	} 
		        else {System.out.println("Failed to delete the file.");    
		        } 
			
		}
		
	}
	
	public static void MoveArquivos(File Arquivo,boolean Status) throws IOException 
	{
		//Recebe um arquivo
		//Valida o arquivo
		//se valido, mover para pasta valido
		//se invalido, mover para pasta invalido
		
		
		// copia os dados
  	    InputStream in;
  	    
  	    // escreve os dados
  	    OutputStream out;
  	    
  	    String caminho;
  	    if(Status) {
  	      
  	    	caminho = "VALIDOS\\"  + Arquivo.getName(); 
  	    
  	    }else {
  	    	caminho = "INVALIDOS\\" + Arquivo.getName();
  	    }
  	    // destino para onde vamos mover o arquivo
  	  File fromFile = new File(caminho);
  	    
  	    
  	//verifica se o arquivo existe
  	  if(!fromFile.exists()){
          //verifica se a pasta existe
          if(!fromFile.getParentFile().exists()){
              //cria a pasta
              fromFile.getParentFile().mkdir();
          }
       // cria o arquivo
          fromFile.createNewFile();
          
  	in = new FileInputStream(Arquivo);
    out = new FileOutputStream(fromFile);
    // buffer para transportar os dados
    byte[] buffer = new byte[1024];
    int length;
    
    // enquanto tiver dados para ler..
    while((length = in.read(buffer)) > 0 ){
        // escreve no novo arquivo
        out.write(buffer, 0 , length);
    }
       in.close();
       out.close();
    
  	  }
	
  	deletaArquivo(Arquivo);
	}

	public static void main(String[] args) throws IOException {
		
	    //Ler todo conteúdo do arquivo
	    BufferedReader ConteudoCSV = null;

	    //Cria uma "linha" Vazia para receber os dados
	    String linha = "";
	    //Separa os campos com ";"
	    String csvSeparadorCompo = ";";
	
	   
	    
	 try {
		 
		 File arquivos[];
		    File diretorio = new File("PENDENTES");
		    arquivos = diretorio.listFiles();
		    
		    for(int i = 0; i < arquivos.length; i++){
		    	   //leia arquivos[i];
		    	
		    	
		    	System.out.println(arquivos[i]);
		    	
	            //Pega o arquivo e manda ler
	          ConteudoCSV = new BufferedReader(new FileReader(arquivos[i]));
	          
	         
	          boolean StatusEntrada = true;
	          //Verifica se o arquivo esta vazio!
	          if(ConteudoCSV.readLine() == null){
	        	  
	        	  StatusEntrada = false;
	          }
	          
	          
	          
	          //enquanto Linha for difente de nulo o codigo continuara!  
	          while((linha = ConteudoCSV.readLine()) != null ){
	        	  //Trata a linha vazia que está no CSV!
	        	  if( linha.trim().isEmpty() ) {
	        		  continue;
	        	  }
	        	  	
	        	  
	        	  
	        	  
	        	  //Vai "contar" a linha a cada ";"
	          String[] venda = linha.split(csvSeparadorCompo);
	          
	      
	          var quant = venda.length;
	          
	          //Aciona o Motedo para mover os arquivos
	          if(quant == 4) {
	           	    
	        	  StatusEntrada = true;
	        	  
	        	  } else {
	        		  StatusEntrada = false;
	        		  break;
	        		  
	        	  }
	       
	       
	          		
	          		
	          	}
	          		//fecha o arquivo
	          	
	          ConteudoCSV.close();
	          MoveArquivos(arquivos[i], StatusEntrada);
	          
		    }
		   
		    //deletaArquivo(DelFile);

	        }
	 
	 		catch(IOException e){
	        e.printStackTrace();
	 		}
	 		
	 		catch(ArrayIndexOutOfBoundsException e){
	           // exceção criada para índice não encontrados
	        System.out.println("índice não encontrado : \n" +e.getMessage());
	        }

	         
	     }

	}



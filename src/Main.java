import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main{
	
	
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException{
		
		Scanner scan = new Scanner(new File("/Users/codkra/Documents/workspace/JavaWorkspace/Keyword Extractor/src/open.txt"));
		PrintWriter writer = new PrintWriter("fixed.csv", "UTF-8");
		Scanner scanColor = new Scanner(new File("/Users/codkra/Documents/workspace/JavaWorkspace/Keyword Extractor/src/colors.csv"));
		ArrayList<String> colors = new ArrayList<String>();
		//print out headings
		for(int i = 0; i < 33; i++){
			writer.print("");
			writer.flush();
			if(i == 26){
				writer.print("Color/finish");
			}
			else if(i == 24){
				writer.print("color with word before");
			}
			else if(i == 25){
				writer.print("color with word after");
			}
			else if(i == 27){
				writer.print("Color/finish");
			}
			else if( i == 28){
				writer.print("ft");
			}
			else if(i == 29){
				writer.print("In");
			}
			else if(i == 30){
				writer.print("Placement");
			}
			else if( i == 31){
				writer.print("Bed Length (SB/LB)");
			}
			else if(i == 32){
				writer.print("Shape");
			}
			writer.print(",");
		}
		writer.println();
		
		//import colors
		while(scanColor.hasNext()){
			colors.add(scanColor.nextLine().toUpperCase());
			
		}
		//loop through the data
			while(scan.hasNext()){
				String[] arrayString = new String[33];
				for(int i = 0; i < 33; i++){
					arrayString[i] = "";
				}
				String word = scan.nextLine().toUpperCase();
				String split[] = word.split("\\s+");
				boolean color = false;
				System.out.println(split.length);
				for(int i = 0; i < split.length; i++){

					int l = split[i].length();
					String test = "";
					if(l > 2){
						 test = split[i].substring(l-2,l);
					}

					//check for feet
					if(test.compareTo("FT")== 0){
						arrayString[28] = split[i];
					}
					else if(split[i].compareTo("SB") == 0 || split[i].compareTo("LB") == 0){
						arrayString[31] = split[i];
					}
					else if(split[i].compareTo("FRONT") == 0 || split[i].compareTo("BACK") == 0 || split[i].compareTo("REAR") == 0){
						arrayString[30] = split[i];
					}
					//check for inches
					else if(test.compareTo("IN") == 0 && Character.isDigit(split[i].charAt(l-3))){
						arrayString[29] = split[i];
					}
					// check for finish
					else if(split[i].compareTo("MATTE") == 0 || split[i].compareTo("GLOSS")==0 || split[i].compareTo("SATIN")==0){
						if(color == false){
							arrayString[26] = split[i] +" " + split[i+1];
							i++;
							color = true;
						}
						else
						{
							arrayString[26] = split[i] + " " + split[i+1];
							i++;
						}

					}
					else if(split[i].compareTo("VENTVISOR-SMOKE") == 0){
						arrayString[26] = "SMOKE";
						color = true;
					}
					else{
						//check for color

						for(int j = 0; j < colors.size(); j++){
							if(split[i].compareTo(colors.get(j)) == 0){
								if(color == false){
									arrayString[26] = split[i];
									color = true;
								}
								else{
									arrayString[27] = split[i];
								}
								if(color = true){
									if(i < split.length - 1){
										arrayString[25] = split[i] +" " + split[i+1];
									}
									if(i > 0){
										arrayString[24] = split[i - 1] + " " + split[i];
									}
									
								}
								
							}
							else{
								arrayString[1] = word;
							}
							
						}
					}
				
				
				}// end for
				for(int i = 0; i < 33; i++){
					writer.print(arrayString[i]);
					writer.print(",");
				}
				writer.println();
				writer.flush();
			}// end while

		
		
		
	}
	
}

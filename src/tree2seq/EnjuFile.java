package tree2seq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EnjuFile {
	//"<cons></cons><cons></cons><cons></cons>"
	private String aSequence = "";
	private ArrayList<String> listBodies = new ArrayList<String>();
	
	public String rewriteXMLCode(String inp) {
		
		String rs = "";
		while (inp.indexOf(">") > 0) {
			String temp = inp.substring(0,inp.indexOf(">")+1);
			rs += removeAttributes(inp.substring(0,inp.indexOf(">")+1));
			//rs += inp.substring(0,inp.indexOf(">")+1) + "\n";
			inp = inp.substring(inp.indexOf(">")+1);
		}
		//System.out.println(rs);
		return rs;
	}
	
	
	/**
	 * remove all attributes
	 * */
	public String removeAttributes(String inp) {
		String rs = "";
		String input = inp;
		int beginTag = input.indexOf("<");
		rs += input.substring(0, beginTag+1);
		input = input.substring(beginTag+1);
		
		
		int space = input.indexOf(" ");
		if (space >= 0) {
			
			rs += input.substring(0, space);
			
			if (input.indexOf("sentence") > -1) {
				rs += " parse_status=\"success\"";
			}
			rs += ">";
			
		}else {
			rs += input;
		}
		
		return rs;
	}
	
	/**
	 * Read file
	 * */
	public void readFile(String fileName) {
		BufferedReader br = null;
		FileReader fr = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				//aSequence += insertEnterChar(sCurrentLine);
				aSequence += rewriteXMLCode(sCurrentLine);
				listBodies.add(rewriteXMLCode(sCurrentLine));
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	/**
	 * write File
	 * */
	public void writeFile(String fileName) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			System.out.println("Starting to write into file\\n");
			//String content = "This is the content to write into file\n";

			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			
			for (String s : listBodies) {
				bw.write(s + "\n");
			}
			//bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
	
	public void printListGits() {
		for (String s : listBodies) {
			System.out.println(s);
		}
		System.out.println(listBodies.size());
	}
	public static void main(String[] args) {
		String test = "<cons></cons><cons></cons><cons></cons>";
		EnjuFile enju = new EnjuFile();
		//enju.insertEnterChar(test);
		enju.readFile("testXML.txt");
		enju.writeFile("train.enju");
		enju.printListGits();
	}
}

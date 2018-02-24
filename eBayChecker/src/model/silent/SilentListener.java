package model.silent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Future;

import model.EbayListener;
import model.item.Item;

public class SilentListener extends EbayListener{

	@Override
	public String doTask(String fileName) throws Exception {
		BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		StringBuilder retStringbuild = new StringBuilder();

				// keep reading till readLine returns null
					while ((lineOut = buff.readLine()) != null) {
						Future<Boolean> retString=null;
						
						Item currentItem = new Item(lineOut);
						retString = threadPool.submit(currentItem);
						
					
						if(!retString.get()){
							retStringbuild.append(lineOut+"\n");
							missingItems.append(lineOut+"\n");
						}
						
					}
				buff.close();
			return retStringbuild.toString();
	}

	public String getFile(String fileName) throws Exception{
		File inFile = new File(fileName);
		BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
		StringBuilder retStringbuild = new StringBuilder();
		if(!inFile.exists()){
			Exception e = new Exception("File Not Exist");
			System.out.println(e.toString()+"\n\nTry loading another file'");
			buff.close();
			throw e;
		}
		String line;
			while ((line = buff.readLine()) != null) {
				retStringbuild.append(line);
			
			}
			buff.close();
		return retStringbuild.toString();

	}


}

private void doSaveUnknownFile(File inFile,File outFile) throws Exception {
  try (FileInputStream in=new FileInputStream(inFile);FileOutputStream out=new FileOutputStream(outFile)){
    System.out.println("[SaveAll]: " + inFile.getName() + " -> " + outFile.getName());
    byte data[]=new byte[1024];
    int count;
    while ((count=in.read(data,0,1024)) != -1) {
      out.write(data,0,count);
    }
  }
 }

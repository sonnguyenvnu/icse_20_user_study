public ByteBuffer getData(){
  try {
    byte[] input=new byte[1024];
    byte[] output=new byte[1024];
    FileInputStream fin=new FileInputStream(file);
    ByteArrayOutputStream bos=new ByteArrayOutputStream();
    Inflater inflater=new Inflater(true);
    while (true) {
      int numRead=fin.read(input);
      if (numRead != -1) {
        inflater.setInput(input,0,numRead);
      }
      int numDecompressed;
      while ((numDecompressed=inflater.inflate(output,0,output.length)) != 0) {
        bos.write(output,0,numDecompressed);
      }
      if (inflater.finished()) {
        break;
      }
 else       if (inflater.needsInput()) {
        continue;
      }
    }
    inflater.end();
    ByteBuffer result=ByteBuffer.wrap(bos.toByteArray(),0,bos.size());
    bos.close();
    fin.close();
    return result;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return null;
}

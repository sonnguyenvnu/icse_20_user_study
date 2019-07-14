/** 
 * @nowebref
 */
static public byte[] loadBytes(File file){
  if (!file.exists()) {
    System.err.println(file + " does not exist, loadBytes() will return null");
    return null;
  }
  try {
    InputStream input;
    int length;
    if (file.getName().toLowerCase().endsWith(".gz")) {
      RandomAccessFile raf=new RandomAccessFile(file,"r");
      raf.seek(raf.length() - 4);
      int b4=raf.read();
      int b3=raf.read();
      int b2=raf.read();
      int b1=raf.read();
      length=(b1 << 24) | (b2 << 16) + (b3 << 8) + b4;
      raf.close();
      input=new BufferedInputStream(new GZIPInputStream(new FileInputStream(file)));
    }
 else {
      long len=file.length();
      int maxArraySize=Integer.MAX_VALUE - 5;
      if (len > maxArraySize) {
        System.err.println("Cannot use loadBytes() on a file larger than " + maxArraySize);
        return null;
      }
      length=(int)len;
      input=new BufferedInputStream(new FileInputStream(file));
    }
    byte[] buffer=new byte[length];
    int count;
    int offset=0;
    while ((count=input.read(buffer,offset,length - offset)) > 0) {
      offset+=count;
    }
    input.close();
    return buffer;
  }
 catch (  IOException e) {
    e.printStackTrace();
    return null;
  }
}

/** 
 * ?????????
 * @param filename
 * @return
 * @throws IOException
 */
public static byte[] getByteFromFile(String filename) throws IOException {
  File f=new File(filename);
  if (!f.exists()) {
    throw new FileNotFoundException(filename);
  }
  ByteArrayOutputStream bos=new ByteArrayOutputStream((int)f.length());
  BufferedInputStream in=null;
  try {
    in=new BufferedInputStream(new FileInputStream(f));
    int buf_size=1024;
    byte[] buffer=new byte[buf_size];
    int len=0;
    while (-1 != (len=in.read(buffer,0,buf_size))) {
      bos.write(buffer,0,len);
    }
    return bos.toByteArray();
  }
 catch (  IOException e) {
    e.printStackTrace();
    throw e;
  }
 finally {
    try {
      in.close();
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
    bos.close();
  }
}

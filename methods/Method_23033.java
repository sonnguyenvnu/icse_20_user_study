/** 
 * Same as PApplet.loadBytes(), however never does gzip decoding.
 */
static public byte[] loadBytesRaw(File file) throws IOException {
  int size=(int)file.length();
  FileInputStream input=new FileInputStream(file);
  byte buffer[]=new byte[size];
  int offset=0;
  int bytesRead;
  while ((bytesRead=input.read(buffer,offset,size - offset)) != -1) {
    offset+=bytesRead;
    if (bytesRead == 0)     break;
  }
  input.close();
  input=null;
  return buffer;
}

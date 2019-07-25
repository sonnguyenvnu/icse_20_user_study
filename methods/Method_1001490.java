/** 
 * Initiates GIF file creation on the given stream. The stream is not closed automatically.
 * @param os OutputStream on which GIF images are written.
 * @return false if initial write failed.
 */
public boolean start(OutputStream os){
  if (os == null)   return false;
  boolean ok=true;
  closeStream=false;
  out=os;
  try {
    writeString("GIF89a");
  }
 catch (  IOException e) {
    ok=false;
  }
  return started=ok;
}

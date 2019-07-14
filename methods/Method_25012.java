/** 
 * Sends the body to the specified OutputStream. The pending parameter limits the maximum amounts of bytes sent unless it is -1, in which case everything is sent.
 * @param outputStream the OutputStream to send data to
 * @param pending -1 to send everything, otherwise sets a max limit to the number of bytes sent
 * @throws IOException if something goes wrong while sending the data.
 */
private void sendBody(OutputStream outputStream,long pending) throws IOException {
  long BUFFER_SIZE=16 * 1024;
  byte[] buff=new byte[(int)BUFFER_SIZE];
  boolean sendEverything=pending == -1;
  while (pending > 0 || sendEverything) {
    long bytesToRead=sendEverything ? BUFFER_SIZE : Math.min(pending,BUFFER_SIZE);
    int read=this.data.read(buff,0,(int)bytesToRead);
    if (read <= 0) {
      break;
    }
    try {
      outputStream.write(buff,0,read);
    }
 catch (    Exception e) {
      if (this.data != null) {
        this.data.close();
      }
    }
    if (!sendEverything) {
      pending-=read;
    }
  }
}

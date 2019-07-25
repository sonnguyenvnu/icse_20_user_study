private void put(final String fileName,final String fileDest) throws IOException {
  createDataSocket();
  sendTransferType(transferType);
  if (fileDest == null) {
    send("STOR " + fileName);
  }
 else {
    send("STOR " + fileDest);
  }
  String reply=receive();
  if (getStatus(reply) == 1) {
    final Socket data=getDataSocket();
    final OutputStream ClientStream=data.getOutputStream();
    final RandomAccessFile inFile=new RandomAccessFile(fileName,"r");
    final byte[] block=new byte[blockSize];
    int numRead;
    while ((numRead=inFile.read(block)) >= 0) {
      ClientStream.write(block,0,numRead);
    }
    inFile.close();
    ClientStream.close();
    data.close();
    reply=receive();
    final boolean success=(getStatus(reply) == 2);
    if (!success) {
      throw new IOException(reply);
    }
  }
 else {
    throw new IOException(reply);
  }
}

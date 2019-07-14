private EncryptionResult createSecureDocument(String path){
  File file=new File(path);
  int length=(int)file.length();
  byte[] b=new byte[length];
  RandomAccessFile f=null;
  try {
    f=new RandomAccessFile(path,"rws");
    f.readFully(b);
  }
 catch (  Exception ignore) {
  }
  EncryptionResult result=encryptData(b);
  try {
    f.seek(0);
    f.write(result.encryptedData);
    f.close();
  }
 catch (  Exception ignore) {
  }
  return result;
}

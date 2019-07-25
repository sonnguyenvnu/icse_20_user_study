public static void download(String source_url,File target_file){
  try {
    ClientConnection connection=new ClientConnection(source_url);
    try {
      OutputStream os=new BufferedOutputStream(new FileOutputStream(target_file));
      int count;
      byte[] buffer=new byte[2048];
      try {
        while ((count=connection.inputStream.read(buffer)) > 0)         os.write(buffer,0,count);
      }
 catch (      IOException e) {
        Data.logger.warn(e.getMessage());
      }
 finally {
        os.close();
      }
    }
 catch (    IOException e) {
      Data.logger.warn(e.getMessage());
    }
 finally {
      connection.close();
    }
  }
 catch (  IOException e) {
    Data.logger.warn(e.getMessage());
  }
}

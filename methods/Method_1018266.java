private void unpack(JarEntry entry,File file) throws IOException {
  InputStream inputStream=this.jarFile.getInputStream(entry,ResourceAccess.ONCE);
  try {
    OutputStream outputStream=new FileOutputStream(file);
    try {
      byte[] buffer=new byte[BUFFER_SIZE];
      int bytesRead;
      while ((bytesRead=inputStream.read(buffer)) != -1) {
        outputStream.write(buffer,0,bytesRead);
      }
      outputStream.flush();
    }
  finally {
      outputStream.close();
    }
  }
  finally {
    inputStream.close();
  }
}

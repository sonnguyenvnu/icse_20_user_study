private void copyResources(File resourcesDirectory) throws IOException {
  InputStream inputStream=getClass().getResourceAsStream("res.zip");
  ZipInputStream zipInputStream=new ZipInputStream(inputStream);
  try {
    ZipEntry zipEntry=zipInputStream.getNextEntry();
    while (zipEntry != null) {
      File file=new File(resourcesDirectory,zipEntry.getName());
      if (zipEntry.isDirectory()) {
        file.mkdir();
      }
 else {
        OutputStream outputStream=new BufferedOutputStream(new FileOutputStream(file),BUFFER_SIZE);
        try {
          int b=zipInputStream.read();
          while (b != -1) {
            outputStream.write(b);
            b=zipInputStream.read();
          }
          outputStream.flush();
        }
  finally {
          outputStream.close();
        }
        file.setLastModified(zipEntry.getTime());
      }
      zipEntry=zipInputStream.getNextEntry();
    }
  }
  finally {
    zipInputStream.close();
  }
}

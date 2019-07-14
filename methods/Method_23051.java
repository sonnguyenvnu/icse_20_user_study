void sendFile(InputStream is,PrintStream ps) throws IOException {
  try {
    int n;
    while ((n=is.read(buf)) > 0) {
      ps.write(buf,0,n);
    }
  }
  finally {
    is.close();
  }
}

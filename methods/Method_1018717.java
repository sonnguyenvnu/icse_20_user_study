@Override public void start() throws IOException {
  if (authToken != null) {
    try {
      NetworkUtil.authToServer(reader,writer,authToken);
    }
 catch (    IOException ioe) {
      shutdown(true);
      throw ioe;
    }
  }
}

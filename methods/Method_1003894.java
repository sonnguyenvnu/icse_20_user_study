@Override public String echo() throws IOException {
  Closer closer=Closer.create();
  try {
    FileReader fileReader=closer.register(new FileReader("/etc/hosts"));
    BufferedReader bufferedReader=closer.register(new BufferedReader(fileReader));
    return bufferedReader.readLine();
  }
  finally {
    closer.close();
  }
}

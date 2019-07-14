protected int readInt(String filename) throws IOException {
  URL url=new URL(filename);
  InputStream stream=url.openStream();
  InputStreamReader isr=new InputStreamReader(stream);
  BufferedReader reader=new BufferedReader(isr);
  return Integer.parseInt(reader.readLine());
}

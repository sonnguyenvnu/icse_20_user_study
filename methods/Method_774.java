public static void load(URL url,Set<String> set) throws IOException {
  InputStream is=null;
  BufferedReader reader=null;
  try {
    is=url.openStream();
    reader=new BufferedReader(new InputStreamReader(is,"utf-8"));
    for (; ; ) {
      String line=reader.readLine();
      if (line == null) {
        break;
      }
      int ci=line.indexOf('#');
      if (ci >= 0) {
        line=line.substring(0,ci);
      }
      line=line.trim();
      if (line.length() == 0) {
        continue;
      }
      set.add(line);
    }
  }
  finally {
    IOUtils.close(reader);
    IOUtils.close(is);
  }
}

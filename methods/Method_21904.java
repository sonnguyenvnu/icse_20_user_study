private static String inputStreamToString(InputStream in) throws IOException {
  return Util.readFully(new InputStreamReader(in,Util.UTF_8));
}

public static String readWholeStream(InputStream in){
  return new Scanner(in).useDelimiter("\\Z").next();
}

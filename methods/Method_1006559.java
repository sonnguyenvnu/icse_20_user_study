public static String slurp(InputStream in){
  Scanner s=new Scanner(in).useDelimiter("\\A");
  return s.hasNext() ? s.next().replaceAll("\r\n","\n").replaceAll("\n",System.getProperty("line.separator")) : "";
}

/** 
 * @nowebref I want to print lines to a file. Why am I always explaining myself? It's the JavaSoft API engineers who need to explain themselves.
 */
static public PrintWriter createWriter(OutputStream output){
  BufferedOutputStream bos=new BufferedOutputStream(output,8192);
  OutputStreamWriter osw=new OutputStreamWriter(bos,StandardCharsets.UTF_8);
  return new PrintWriter(osw);
}

/** 
 * @nowebref
 */
static public void saveStrings(OutputStream output,String[] data){
  PrintWriter writer=createWriter(output);
  for (int i=0; i < data.length; i++) {
    writer.println(data[i]);
  }
  writer.flush();
  writer.close();
}

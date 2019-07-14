/** 
 * @nowebref
 */
static public JSONObject loadJSONObject(File file){
  BufferedReader reader=createReader(file);
  JSONObject outgoing=new JSONObject(reader);
  try {
    reader.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return outgoing;
}

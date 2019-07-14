/** 
 * @webref input:files
 * @param filename name of a file in the data folder or a URL
 * @see JSONObject
 * @see JSONArray
 * @see PApplet#loadJSONArray(String)
 * @see PApplet#saveJSONObject(JSONObject,String)
 * @see PApplet#saveJSONArray(JSONArray,String)
 */
public JSONObject loadJSONObject(String filename){
  BufferedReader reader=createReader(filename);
  JSONObject outgoing=new JSONObject(reader);
  try {
    reader.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return outgoing;
}

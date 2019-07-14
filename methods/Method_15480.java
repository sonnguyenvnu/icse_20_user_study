/** 
 * ???key??
 * @param array
 * @return
 */
public static JSONArray format(final JSONArray array){
  if (array == null || array.isEmpty()) {
    Log.i(TAG,"format  array == null || array.isEmpty() >> return array;");
    return array;
  }
  JSONArray formatedArray=new JSONArray();
  Object value;
  for (int i=0; i < array.size(); i++) {
    value=array.get(i);
    if (value instanceof JSONArray) {
      formatedArray.add(format((JSONArray)value));
    }
 else     if (value instanceof JSONObject) {
      formatedArray.add(format((JSONObject)value));
    }
 else {
      formatedArray.add(value);
    }
  }
  return formatedArray;
}

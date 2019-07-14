/** 
 * ??JSONArray
 * @param tv
 * @return
 */
@NotNull public static JSONArray newJSONArray(Object obj){
  JSONArray array=new JSONArray();
  if (obj != null) {
    if (obj instanceof Collection) {
      array.addAll((Collection<?>)obj);
    }
 else {
      array.add(obj);
    }
  }
  return array;
}

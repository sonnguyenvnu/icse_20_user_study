/** 
 * Encodes  {@code data} as a JSON string. This applies quotes and anynecessary character escaping.
 * @param data the string to encode. Null will be interpreted as an emptystring.
 * @return the quoted string.
 */
public static String quote(String data){
  if (data == null) {
    return "\"\"";
  }
  try {
    JSONStringer stringer=new JSONStringer();
    stringer.open(JSONStringer.Scope.NULL,"");
    stringer.value(data);
    stringer.close(JSONStringer.Scope.NULL,JSONStringer.Scope.NULL,"");
    return stringer.toString();
  }
 catch (  JSONException e) {
    throw new AssertionError();
  }
}

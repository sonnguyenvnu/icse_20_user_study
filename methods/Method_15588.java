/** 
 * ????
 * @param function
 * @param request
 * @param isSQLFunction
 * @return
 * @throws Exception
 */
@NotNull public static FunctionBean parseFunction(@NotNull String function,@NotNull JSONObject request,boolean isSQLFunction) throws Exception {
  int start=function.indexOf("(");
  int end=function.lastIndexOf(")");
  String method=end != function.length() - 1 ? null : function.substring(0,start);
  if (StringUtil.isEmpty(method,true)) {
    throw new IllegalArgumentException("?? " + function + " ????????? function ?????" + "???? function(key0,key1,...) ????????" + "\nfunction???? " + (isSQLFunction ? "SQL ??/SQL ????" : "Java ??") + " ???key ???? request ??????");
  }
  String[] keys=StringUtil.split(function.substring(start + 1,end));
  int length=keys == null ? 0 : keys.length;
  Class<?>[] types;
  Object[] values;
  if (isSQLFunction) {
    types=new Class<?>[length];
    values=new Object[length];
    Object v;
    for (int i=0; i < length; i++) {
      v=values[i]=request.get(keys[i]);
      if (v == null) {
        types[i]=Object.class;
        values[i]=null;
        break;
      }
      if (v instanceof Boolean) {
        types[i]=Boolean.class;
      }
 else       if (v instanceof Number) {
        types[i]=Number.class;
      }
 else       if (v instanceof String) {
        types[i]=String.class;
      }
 else       if (v instanceof JSONObject) {
        types[i]=JSONObject.class;
      }
 else       if (v instanceof JSONArray) {
        types[i]=JSONArray.class;
      }
 else {
        throw new UnsupportedDataTypeException(keys[i] + ":value ?value???????? key():" + function + " ??arg??????" + "??? [Boolean, Number, String, JSONObject, JSONArray] ?????");
      }
    }
  }
 else {
    types=new Class<?>[length + 1];
    types[0]=JSONObject.class;
    values=new Object[length + 1];
    values[0]=request;
    for (int i=0; i < length; i++) {
      types[i + 1]=String.class;
      values[i + 1]=keys[i];
    }
  }
  FunctionBean fb=new FunctionBean();
  fb.setFunction(function);
  fb.setMethod(method);
  fb.setKeys(keys);
  fb.setTypes(types);
  fb.setValues(values);
  return fb;
}

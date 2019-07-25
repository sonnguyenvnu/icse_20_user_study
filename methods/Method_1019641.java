/** 
 * Wrap an object, if necessary. If the object is null, return the NULL object. If it is an array or collection, wrap it in a JSONArray. If it is a map, wrap it in a JSONObject. If it is a standard property (Double, String, et al) then it is already wrapped. Otherwise, if it comes from one of the java packages, turn it into a string. And if it doesn't, try to wrap it in a JSONObject. If the wrapping fails, then null is returned.
 * @param object The object to wrap
 * @return The wrapped value
 */
public static Object wrap(Object object,IJSONNode node){
  try {
    if (object == null) {
      return NULL;
    }
    if (object instanceof JSONObject || object instanceof JSONArray || NULL.equals(object) || object instanceof JSONString || object instanceof Byte || object instanceof Character || object instanceof Short || object instanceof Integer || object instanceof Long || object instanceof Boolean || object instanceof Float || object instanceof Double || object instanceof String) {
      return object;
    }
    boolean isUpperCaseFirstChar=node != null ? node.isUpperCaseFirstChar() : DEFAULT_UPPER_CASE;
    if (object instanceof Collection) {
      return new JSONArray((Collection)object,isUpperCaseFirstChar);
    }
    if (object.getClass().isArray()) {
      return new JSONArray(object,isUpperCaseFirstChar);
    }
    if (object instanceof Map) {
      return new JSONObject((Map)object,isUpperCaseFirstChar);
    }
    if (object instanceof Date) {
      return new JSONObject((Date)object);
    }
    if (object instanceof InputStream) {
      return new JSONObject((InputStream)object);
    }
    Package objectPackage=object.getClass().getPackage();
    String objectPackageName=objectPackage != null ? objectPackage.getName() : "";
    if (objectPackageName.startsWith("java.") || objectPackageName.startsWith("javax.") || object.getClass().getClassLoader() == null) {
      return object.toString();
    }
    return new JSONObject(object,isUpperCaseFirstChar);
  }
 catch (  Exception exception) {
    return null;
  }
}

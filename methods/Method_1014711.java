private static Object wrap(Object o){
  if (o == null) {
    return null;
  }
  if (o instanceof JSONArray || o instanceof JSONObject) {
    return o;
  }
  try {
    if (o instanceof Collection) {
      return collection2Json((Collection<?>)o);
    }
 else     if (o.getClass().isArray()) {
      return object2Json(o);
    }
    if (o instanceof Map) {
      return map2Json((Map<?,?>)o);
    }
    if (o instanceof Boolean || o instanceof Byte || o instanceof Character || o instanceof Double || o instanceof Float || o instanceof Integer || o instanceof Long || o instanceof Short || o instanceof String) {
      return o;
    }
    if (o.getClass().getPackage().getName().startsWith("java.")) {
      return o.toString();
    }
  }
 catch (  Exception ignored) {
  }
  return null;
}

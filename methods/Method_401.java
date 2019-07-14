public static boolean contains(Object rootObject,String path){
  if (rootObject == null) {
    return false;
  }
  JSONPath jsonpath=compile(path);
  return jsonpath.contains(rootObject);
}

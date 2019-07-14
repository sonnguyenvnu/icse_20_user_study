private static Class<?> maybeForName(String name){
  try {
    return Class.forName(name);
  }
 catch (  ClassNotFoundException cnfe) {
    return null;
  }
catch (  RuntimeException re) {
    Log.w(TAG,"Unexpected error while finding class " + name,re);
    return null;
  }
}

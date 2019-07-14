private static void clearMap(final Class mapClass,final Object map,final String fieldName) throws Exception {
  Field field=mapClass.getDeclaredField(fieldName);
  field.setAccessible(true);
  Object cache=field.get(map);
synchronized (cache) {
    Class ccl=cache.getClass();
    Method clearMethod=ccl.getMethod("clear");
    clearMethod.invoke(cache);
  }
}

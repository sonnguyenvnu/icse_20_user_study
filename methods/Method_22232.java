/** 
 * Retrieves key/value pairs from static getters of a class (get*() or is*()).
 * @param someClass the class to be inspected.
 */
private void collectStaticGettersResults(@NonNull Class<?> someClass,@NonNull JSONObject container) throws JSONException {
  final Method[] methods=someClass.getMethods();
  for (  final Method method : methods) {
    if (method.getParameterTypes().length == 0 && (method.getName().startsWith("get") || method.getName().startsWith("is")) && !"getClass".equals(method.getName())) {
      try {
        container.put(method.getName(),method.invoke(null,(Object[])null));
      }
 catch (      @NonNull IllegalArgumentException ignored) {
      }
catch (      @NonNull InvocationTargetException ignored) {
      }
catch (      @NonNull IllegalAccessException ignored) {
      }
    }
  }
}

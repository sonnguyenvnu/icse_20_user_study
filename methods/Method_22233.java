@NonNull private JSONObject collectSettings(@NonNull Context context,@NonNull CoreConfiguration config,@NonNull Class<?> settings) throws NoSuchMethodException {
  final JSONObject result=new JSONObject();
  final Field[] keys=settings.getFields();
  final Method getString=settings.getMethod("getString",ContentResolver.class,String.class);
  for (  final Field key : keys) {
    if (!key.isAnnotationPresent(Deprecated.class) && key.getType() == String.class && isAuthorized(config,key)) {
      try {
        final Object value=getString.invoke(null,context.getContentResolver(),key.get(null));
        if (value != null) {
          result.put(key.getName(),value);
        }
      }
 catch (      @NonNull Exception e) {
        ACRA.log.w(LOG_TAG,ERROR,e);
      }
    }
  }
  return result;
}

/** 
 * This horrible hack is required on Android, due to implementation of BasicManagedEntity, which doesn't chain call consumeContent on underlying wrapped HttpEntity
 * @param entity HttpEntity, may be null
 */
public static void endEntityViaReflection(HttpEntity entity){
  if (entity instanceof HttpEntityWrapper) {
    try {
      Field f=null;
      Field[] fields=HttpEntityWrapper.class.getDeclaredFields();
      for (      Field ff : fields) {
        if (ff.getName().equals("wrappedEntity")) {
          f=ff;
          break;
        }
      }
      if (f != null) {
        f.setAccessible(true);
        HttpEntity wrapped=(HttpEntity)f.get(entity);
        if (wrapped != null) {
          wrapped.consumeContent();
        }
      }
    }
 catch (    Throwable t) {
      log.e(LOG_TAG,"wrappedEntity consume",t);
    }
  }
}

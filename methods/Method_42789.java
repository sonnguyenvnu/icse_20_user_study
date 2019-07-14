/** 
 * ???????NULL,???????????Collection?Map(??????????????)
 * @param obj ??????
 * @param message ????
 */
@SuppressWarnings("rawtypes") public static void checkNotEmpty(Object obj,String message){
  if (obj == null) {
    throw new IllegalArgumentException(message);
  }
  if (obj instanceof String && obj.toString().trim().length() == 0) {
    throw new IllegalArgumentException(message);
  }
  if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
    throw new IllegalArgumentException(message);
  }
  if (obj instanceof Collection && ((Collection)obj).isEmpty()) {
    throw new IllegalArgumentException(message);
  }
  if (obj instanceof Map && ((Map)obj).isEmpty()) {
    throw new IllegalArgumentException(message);
  }
}

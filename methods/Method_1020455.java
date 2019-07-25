/** 
 * Boxes a JsEnum value that does not support comparable. 
 */
public static <T>BoxedLightEnum<T> box(T value,Constructor ctor){
  if (value == null) {
    return null;
  }
  return cache(ctor,"$$enumValues/" + value,() -> new BoxedLightEnum<T>(value,ctor));
}

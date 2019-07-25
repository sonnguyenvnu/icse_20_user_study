/** 
 * ??????????
 * @see Object#getClass()
 */
private Class<?> type(){
  if (clazz != null) {
    return clazz;
  }
  if (isClass) {
    return (Class<?>)object;
  }
 else {
    return object.getClass();
  }
}

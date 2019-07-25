/** 
 * clear method not found in Spring 4.0.
 * @param obj
 * @param param
 */
private void clear(InjectionMetadata obj,PropertyValues param){
  if (!clearInited) {
    try {
      cleanMethod=InjectionMetadata.class.getMethod("clear",PropertyValues.class);
    }
 catch (    NoSuchMethodException e) {
    }
    clearInited=true;
  }
  if (cleanMethod != null) {
    try {
      cleanMethod.invoke(obj,param);
    }
 catch (    IllegalAccessException|InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }
}

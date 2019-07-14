/** 
 * Applies advice on given target class and returns proxy instance.
 */
public static <T>T applyAdvice(final Class<T> targetClass){
  Class adviceClass=cache.get(targetClass);
  if (adviceClass == null) {
    adviceClass=PROXY_PROXETTA.proxy().setTarget(targetClass).define();
    cache.put(targetClass,adviceClass);
  }
  try {
    Object advice=ClassUtil.newInstance(adviceClass);
    Field field=adviceClass.getField("$___target$0");
    field.set(advice,targetClass);
    return (T)advice;
  }
 catch (  Exception ex) {
    throw new ProxettaException(ex);
  }
}

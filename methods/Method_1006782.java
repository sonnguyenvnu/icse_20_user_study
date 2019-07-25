/** 
 * ??setter???
 * @param clz
 * @param origin
 */
public static <T>T copy(Class<T> clz,Object origin){
  if (origin == null)   return null;
  T t=null;
  String p="";
  Object v=null;
  try {
    t=clz.newInstance();
    Class oc=origin.getClass();
    ReflectionCache originCache=Parser.getReflectionCache(oc);
    ReflectionCache cache=Parser.getReflectionCache(clz);
    for (    FieldAndMethod fnm : cache.getMap().values()) {
      FieldAndMethod originFnm=originCache.get(fnm.getProperty());
      if (originFnm == null) {
        originFnm=originCache.getTemp(fnm.getProperty());
        if (originFnm == null) {
          originFnm=new FieldAndMethod();
          originCache.getTempMap().put(fnm.getProperty(),originFnm);
          String getterName=fnm.getGetterName();
          Method orginGetter=null;
          try {
            orginGetter=oc.getDeclaredMethod(getterName);
          }
 catch (          Exception e) {
          }
          if (orginGetter != null) {
            originFnm.setGetter(orginGetter);
            originFnm.setGetterName(getterName);
            String setterName=fnm.getSetterName();
            Method orginSetter=null;
            try {
              orginSetter=oc.getDeclaredMethod(setterName,fnm.getField().getType());
            }
 catch (            Exception e) {
            }
            if (orginSetter != null) {
              originFnm.setSetter(orginSetter);
              originFnm.setSetterName(setterName);
            }
            originFnm.setProperty(fnm.getProperty());
          }
        }
      }
      try {
        if (originFnm != null && originFnm.getGetterName() != null) {
          v=oc.getDeclaredMethod(originFnm.getGetterName()).invoke(origin);
          Method m=fnm.getSetter();
          m.invoke(t,v);
        }
      }
 catch (      Exception e) {
      }
    }
  }
 catch (  Exception e) {
    System.out.println("p = " + p + ", v = " + v);
    e.printStackTrace();
  }
  return t;
}

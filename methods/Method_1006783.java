/** 
 * ??setter???
 * @param target
 * @param origin
 */
public static void copy(Object target,Object origin){
  if (origin == null || target == null)   return;
  try {
    Class clz=target.getClass();
    Class oc=origin.getClass();
    Method[] originMethodArr=oc.getDeclaredMethods();
    Set<String> methodSet=new HashSet<String>();
    for (    Method m : originMethodArr) {
      methodSet.add(m.getName());
    }
    for (    Method m : clz.getDeclaredMethods()) {
      if (m.getName().startsWith("set")) {
        String p="";
        if (m.getParameterTypes()[0] == boolean.class || m.getParameterTypes()[0] == Boolean.class) {
          p=getPropertyOfBoolen(m.getName());
        }
 else {
          p=getProperty(m.getName());
        }
        String getter=getGetter(p);
        if (!methodSet.contains(getter)) {
          continue;
        }
        Object v=null;
        try {
          v=oc.getDeclaredMethod(getter).invoke(origin);
        }
 catch (        Exception e) {
        }
        if (v != null) {
          m.invoke(target,v);
        }
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}

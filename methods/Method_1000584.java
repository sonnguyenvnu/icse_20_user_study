/** 
 * ????????? getter ??????? public ? Field ??
 * @param obj ??
 * @return ??
 */
public static String obj(Object obj){
  if (null == obj)   return "null";
  StringBuilder sb=new StringBuilder(obj.getClass().getName() + "\n\n[Fields:]");
  Mirror<?> mirror=Mirror.me(obj.getClass());
  for (  Field f : mirror.getType().getFields())   if (Modifier.isPublic(f.getModifiers()))   try {
    sb.append(String.format("\n\t%10s : %s",f.getName(),f.get(obj)));
  }
 catch (  Exception e1) {
    sb.append(String.format("\n\t%10s : %s",f.getName(),e1.getMessage()));
  }
  sb.append("\n\n[Methods:]");
  for (  Method m : mirror.getType().getMethods())   if (Modifier.isPublic(m.getModifiers()))   if (m.getName().startsWith("get"))   if (m.getParameterTypes().length == 0)   try {
    sb.append(String.format("\n\t%10s : %s",m.getName(),m.invoke(obj)));
  }
 catch (  Exception e) {
    sb.append(String.format("\n\t%10s : %s",m.getName(),e.getMessage()));
  }
  return sb.toString();
}

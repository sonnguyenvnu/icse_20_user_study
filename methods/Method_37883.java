/** 
 * Returns all superclasses.
 */
public static Class[] getSuperclasses(final Class type){
  int i=0;
  for (Class x=type.getSuperclass(); x != null; x=x.getSuperclass()) {
    i++;
  }
  Class[] result=new Class[i];
  i=0;
  for (Class x=type.getSuperclass(); x != null; x=x.getSuperclass()) {
    result[i]=x;
    i++;
  }
  return result;
}

/** 
 * Returns a new array with a single element appended at the end. Use this sparingly, for it will allocate a new array.  You can easily turn a linear-time algorithm to quadratic this way.
 * @param v Original array
 * @param elem Element to add to end
 * @return Array with length v+1 that is (v0,v1,...,vn,elem).Runtime type will be same as he pased-in array.
 */
public static Object[] append(Object[] v,Object elem){
  Object[] ret=(Object[])Array.newInstance(v.getClass().getComponentType(),v.length + 1);
  System.arraycopy(v,0,ret,0,v.length);
  ret[v.length]=elem;
  return ret;
}

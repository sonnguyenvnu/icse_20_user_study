/** 
 * Concatenates 2 arrays
 */
public static <T>T[] concat(T[] one,T[] other,Class<T> clazz){
  T[] target=(T[])Array.newInstance(clazz,one.length + other.length);
  System.arraycopy(one,0,target,0,one.length);
  System.arraycopy(other,0,target,one.length,other.length);
  return target;
}

/** 
 * Returns a new array with a single element appended at the end. Use this sparingly, for it will allocate a new array.  You can easily turn a linear-time algorithm to quadratic this way.
 * @param v Original array
 * @param elem Element to add to end
 */
public static int[] append(int[] v,int elem){
  int[] ret=new int[v.length + 1];
  System.arraycopy(v,0,ret,0,v.length);
  ret[v.length]=elem;
  return ret;
}

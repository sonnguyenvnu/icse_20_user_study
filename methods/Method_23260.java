/** 
 * Use arrayCopy() instead.
 */
@Deprecated static public void arraycopy(Object src,Object dst){
  System.arraycopy(src,0,dst,0,Array.getLength(src));
}

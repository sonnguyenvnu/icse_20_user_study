/** 
 * Shortcut to copy the entire contents of the source into the destination array. Identical to <CODE>arraycopy(src, 0, dst, 0, src.length);</CODE>
 */
static public void arrayCopy(Object src,Object dst){
  System.arraycopy(src,0,dst,0,Array.getLength(src));
}

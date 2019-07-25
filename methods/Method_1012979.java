/** 
 * Returns the size of this data structure, minimized with  {@link Integer#MAX_VALUE}. <p>This default implementation follows the definition above, which is compatible with  {@link Collection#size()}.
 * @return the size of this data structure, minimized with {@link Integer#MAX_VALUE}.
 * @see java.util.Collection#size()
 * @deprecated Use {@link #size64()} instead.
 */
@Deprecated default int size(){
  return (int)Math.min(Integer.MAX_VALUE,size64());
}

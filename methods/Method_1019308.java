/** 
 * Truncate or expand the list to the new size. If the list is truncated, the buffer will not be reallocated (use  {@link #trimToSize()} if you need atruncated buffer), but the truncated values will be reset to the default value (zero). If the list is expanded, the elements beyond the current size are initialized with JVM-defaults (zero or <code>null</code> values).
 */
public void resize(int newSize){
  if (newSize <= buffer.length) {
    if (newSize < elementsCount) {
      Arrays.fill(buffer,newSize,elementsCount,Intrinsics.empty());
    }
 else {
      Arrays.fill(buffer,elementsCount,newSize,Intrinsics.empty());
    }
  }
 else {
    ensureCapacity(newSize);
  }
  this.elementsCount=newSize;
}

/** 
 * Skip over that many entries. This method is relatively fast (for this map implementation) even if many entries need to be skipped.
 * @param n the number of entries to skip
 */
public void skip(long n){
  if (n < 10) {
    while (n-- > 0 && hasNext()) {
      next();
    }
  }
 else   if (hasNext()) {
    assert cursorPos != null;
    CursorPos cp=cursorPos;
    CursorPos parent;
    while ((parent=cp.parent) != null)     cp=parent;
    Page root=cp.page;
    @SuppressWarnings("unchecked") MVMap<K,?> map=(MVMap<K,?>)root.map;
    long index=map.getKeyIndex(next());
    last=map.getKey(index + n);
    this.cursorPos=traverseDown(root,last);
  }
}

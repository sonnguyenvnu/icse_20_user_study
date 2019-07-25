/** 
 * Add the value if it is not already present, otherwise remove it.
 * @param x integer value
 */
public void flip(final int x){
  final short hb=BufferUtil.highbits(x);
  final int i=highLowContainer.getIndex(hb);
  if (i >= 0) {
    MappeableContainer c=highLowContainer.getContainerAtIndex(i);
    c=c.flip(BufferUtil.lowbits(x));
    if (!c.isEmpty()) {
      ((MutableRoaringArray)highLowContainer).setContainerAtIndex(i,c);
    }
 else {
      ((MutableRoaringArray)highLowContainer).removeAtIndex(i);
    }
  }
 else {
    final MappeableArrayContainer newac=new MappeableArrayContainer();
    ((MutableRoaringArray)highLowContainer).insertNewKeyValueAt(-i - 1,hb,newac.add(BufferUtil.lowbits(x)));
  }
}

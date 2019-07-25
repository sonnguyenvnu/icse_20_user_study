/** 
 * Add the value if it is not already present, otherwise remove it.
 * @param x integer value
 */
public void flip(final int x){
  final short hb=Util.highbits(x);
  final int i=highLowContainer.getIndex(hb);
  if (i >= 0) {
    Container c=highLowContainer.getContainerAtIndex(i).flip(Util.lowbits(x));
    if (!c.isEmpty()) {
      highLowContainer.setContainerAtIndex(i,c);
    }
 else {
      highLowContainer.removeAtIndex(i);
    }
  }
 else {
    final ArrayContainer newac=new ArrayContainer();
    highLowContainer.insertNewKeyValueAt(-i - 1,hb,newac.add(Util.lowbits(x)));
  }
}

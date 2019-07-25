/** 
 * Remove from the current bitmap all integers in [rangeStart,rangeEnd).
 * @param rangeStart inclusive beginning of range
 * @param rangeEnd exclusive ending of range
 */
public void remove(final long rangeStart,final long rangeEnd){
  rangeSanityCheck(rangeStart,rangeEnd);
  if (rangeStart >= rangeEnd) {
    return;
  }
  final int hbStart=Util.toIntUnsigned(Util.highbits(rangeStart));
  final int lbStart=Util.toIntUnsigned(Util.lowbits(rangeStart));
  final int hbLast=Util.toIntUnsigned(Util.highbits(rangeEnd - 1));
  final int lbLast=Util.toIntUnsigned(Util.lowbits(rangeEnd - 1));
  if (hbStart == hbLast) {
    final int i=highLowContainer.getIndex((short)hbStart);
    if (i < 0) {
      return;
    }
    final Container c=highLowContainer.getContainerAtIndex(i).iremove(lbStart,lbLast + 1);
    if (!c.isEmpty()) {
      highLowContainer.setContainerAtIndex(i,c);
    }
 else {
      highLowContainer.removeAtIndex(i);
    }
    return;
  }
  int ifirst=highLowContainer.getIndex((short)hbStart);
  int ilast=highLowContainer.getIndex((short)hbLast);
  if (ifirst >= 0) {
    if (lbStart != 0) {
      final Container c=highLowContainer.getContainerAtIndex(ifirst).iremove(lbStart,Util.maxLowBitAsInteger() + 1);
      if (!c.isEmpty()) {
        highLowContainer.setContainerAtIndex(ifirst,c);
        ifirst++;
      }
    }
  }
 else {
    ifirst=-ifirst - 1;
  }
  if (ilast >= 0) {
    if (lbLast != Util.maxLowBitAsInteger()) {
      final Container c=highLowContainer.getContainerAtIndex(ilast).iremove(0,lbLast + 1);
      if (!c.isEmpty()) {
        highLowContainer.setContainerAtIndex(ilast,c);
      }
 else {
        ilast++;
      }
    }
 else {
      ilast++;
    }
  }
 else {
    ilast=-ilast - 1;
  }
  highLowContainer.removeIndexRange(ifirst,ilast);
}

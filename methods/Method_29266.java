/** 
 * ??slot??
 * @param startSlot
 * @param endSlot
 * @return
 */
private List<Integer> getStartToEndSlotList(int startSlot,int endSlot){
  List<Integer> slotList=new ArrayList<Integer>();
  if (startSlot == endSlot) {
    slotList.add(startSlot);
  }
 else {
    for (int i=startSlot; i <= endSlot; i++) {
      slotList.add(i);
    }
  }
  return slotList;
}

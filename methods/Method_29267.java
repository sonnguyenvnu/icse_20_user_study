/** 
 * 0,4096 0-4096 2,2 2-2
 * @param slotInfo
 * @return
 */
private String getStartToEndSlotDistribute(int startSlot,int endSlot){
  if (startSlot == endSlot) {
    return String.valueOf(startSlot);
  }
 else {
    return startSlot + "-" + endSlot;
  }
}

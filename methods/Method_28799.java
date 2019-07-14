private List<Integer> getAssignedSlotArray(List<Object> slotInfo){
  List<Integer> slotNums=new ArrayList<Integer>();
  for (int slot=((Long)slotInfo.get(0)).intValue(); slot <= ((Long)slotInfo.get(1)).intValue(); slot++) {
    slotNums.add(slot);
  }
  return slotNums;
}

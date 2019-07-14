private String[] extractSlotParts(String[] nodeInfoPartArray){
  String[] slotInfoPartArray=new String[nodeInfoPartArray.length - SLOT_INFORMATIONS_START_INDEX];
  for (int i=SLOT_INFORMATIONS_START_INDEX; i < nodeInfoPartArray.length; i++) {
    slotInfoPartArray[i - SLOT_INFORMATIONS_START_INDEX]=nodeInfoPartArray[i];
  }
  return slotInfoPartArray;
}

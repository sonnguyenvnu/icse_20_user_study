private void fillSlotInformationFromSlotRange(String slotRange,ClusterNodeInformation info){
  if (slotRange.startsWith(SLOT_IN_TRANSITION_IDENTIFIER)) {
    int slot=Integer.parseInt(slotRange.substring(1).split("-")[0]);
    if (slotRange.contains(SLOT_IMPORT_IDENTIFIER)) {
      info.addSlotBeingImported(slot);
    }
 else {
      info.addSlotBeingMigrated(slot);
    }
  }
 else   if (slotRange.contains("-")) {
    String[] slotRangePart=slotRange.split("-");
    for (int slot=Integer.valueOf(slotRangePart[0]); slot <= Integer.valueOf(slotRangePart[1]); slot++) {
      info.addAvailableSlot(slot);
    }
  }
 else {
    info.addAvailableSlot(Integer.valueOf(slotRange));
  }
}

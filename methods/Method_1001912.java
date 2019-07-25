public SlotSet reverse(){
  final SlotSet result=new SlotSet();
  Collections.sort(all);
  Slot last=null;
  for (  Slot slot : all) {
    if (last != null) {
      result.addSlot(last.getEnd(),slot.getStart());
    }
    last=slot;
  }
  return result;
}

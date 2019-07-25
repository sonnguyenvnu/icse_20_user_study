public SlotSet smaller(double margin){
  final SlotSet result=new SlotSet();
  for (  Slot sl : all) {
    if (sl.size() <= 2 * margin) {
      continue;
    }
    result.addSlot(sl.getStart() + margin,sl.getEnd() - margin);
  }
  return result;
}

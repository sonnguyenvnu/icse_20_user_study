private boolean addTransition(ArrayList<Transition> transitions,Transition tr){
  int size=transitions.size();
  if (size == 0) {
    transitions.add(tr);
    return true;
  }
  Transition last=transitions.get(size - 1);
  if (!tr.isTransitionFrom(last)) {
    return false;
  }
  int offsetForLast=0;
  if (size >= 2) {
    offsetForLast=transitions.get(size - 2).getWallOffset();
  }
  int offsetForNew=last.getWallOffset();
  long lastLocal=last.getMillis() + offsetForLast;
  long newLocal=tr.getMillis() + offsetForNew;
  if (newLocal != lastLocal) {
    transitions.add(tr);
    return true;
  }
  Transition previous=transitions.remove(size - 1);
  Transition adjusted=tr.withMillis(previous.getMillis());
  return addTransition(transitions,adjusted);
}

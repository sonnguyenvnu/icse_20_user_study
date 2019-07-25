public static Tabs apply(Tabs source,Tabs destination){
  CTTabStop sourceTabStop=null;
  CTTabStop destinationTabStop=null;
  if (!isEmpty(source)) {
    if (destination == null)     destination=Context.getWmlObjectFactory().createTabs();
    destination.getTab().clear();
    for (int i=0; i < source.getTab().size(); i++) {
      sourceTabStop=source.getTab().get(i);
      destinationTabStop=Context.getWmlObjectFactory().createCTTabStop();
      destinationTabStop.setLeader(sourceTabStop.getLeader());
      destinationTabStop.setPos(sourceTabStop.getPos());
      destinationTabStop.setVal(sourceTabStop.getVal());
      if (destinationTabStop != null) {
        destination.getTab().add(destinationTabStop);
      }
    }
  }
  return destination;
}

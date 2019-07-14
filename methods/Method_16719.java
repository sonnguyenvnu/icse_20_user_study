@Override public List<ActivityImpl> getNextEvent(ActivityImpl activity){
  return activity.getOutgoingTransitions().stream().map(PvmTransition::getDestination).map(ActivityImpl.class::cast).collect(Collectors.toList());
}

public FrontierSimple add(double delta,ParticipantRange range){
  if (range == null) {
    throw new IllegalArgumentException();
  }
  return new FrontierSimple(freeY + delta);
}

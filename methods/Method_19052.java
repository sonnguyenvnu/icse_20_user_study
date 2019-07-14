protected boolean shouldUpdate(Section previous,Section next){
  return !(previous == next || (previous != null && previous.isEquivalentTo(next)));
}

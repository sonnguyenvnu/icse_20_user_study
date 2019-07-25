public void validate(){
  ListSequence.fromList(errors).clear();
  if (startingTarget != null && !(targetRange.hasTarget(startingTarget))) {
    error(startingTarget,"unknown starting target: " + startingTarget);
  }
  if (!(targetRange.hasTarget(finalTarget))) {
    error(finalTarget,"unknown final target: " + finalTarget);
  }
  if (targetRange.hasCycles()) {
    error(this,"cycle(s) detected: " + targetRange.cycles());
  }
  if (startingTarget != null && !(Sequence.fromIterable(targetRange.targetAndSortedPrecursors(finalTarget)).select(new ISelector<ITarget,ITarget.Name>(){
    public ITarget.Name select(    ITarget t){
      return t.getName();
    }
  }
).contains(startingTarget))) {
    error(this,"invalid starting target: " + startingTarget);
  }
  validated=true;
}

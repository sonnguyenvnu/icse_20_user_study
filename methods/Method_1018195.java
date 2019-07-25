public void union(Action anotherAction){
  List<Action> existingActions=this.getActions();
  List<Action> otherActions=anotherAction.getActions();
  List<Action> newActions=new ArrayList<>();
  for (  Action otherAction : otherActions) {
    Action existingAction=this.getAction(otherAction.getSystemName());
    if (existingAction != null) {
      existingAction.union(otherAction);
    }
 else {
      newActions.add(otherAction);
    }
  }
  existingActions.addAll(newActions);
}

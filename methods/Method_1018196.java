public void union(ActionGroup otherGroup){
  List<Action> existingActions=actionSet.getActions();
  List<Action> otherActions=otherGroup.getActions();
  List<Action> newActions=new ArrayList<>();
  for (  Action otherAction : otherActions) {
    Action existingAction=actionSet.getAction(otherAction.getSystemName());
    if (existingAction != null) {
      existingAction.union(otherAction);
    }
 else {
      newActions.add(otherAction);
    }
  }
  existingActions.addAll(newActions);
}

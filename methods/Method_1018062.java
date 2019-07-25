@Override public boolean enable(Principal principal,Action action,Action... more){
  Set<Action> actions=new HashSet<>(Arrays.asList(more));
  actions.add(action);
  return enable(principal,actions);
}

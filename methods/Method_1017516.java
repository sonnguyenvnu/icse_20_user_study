@Override public ChoiceTransitionConfigurer<S,E> first(S target,Guard<S,E> guard,Action<S,E> action,Action<S,E> error){
  Collection<Action<S,E>> actions=new ArrayList<>();
  if (action != null) {
    actions.add(error != null ? Actions.errorCallingAction(action,error) : action);
  }
  this.first=new ChoiceData<S,E>(source,target,guard,actions);
  return this;
}

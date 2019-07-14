public Choice<State<List<JCTree>>> unify(@Nullable Iterable<? extends Tree> nodes,State<?> state){
  if (nodes == null) {
    return Choice.of(state.<List<JCTree>>withResult(null));
  }
  Choice<State<List<JCTree>>> choice=Choice.of(state.withResult(List.<JCTree>nil()));
  for (  final Tree node : nodes) {
    choice=choice.thenChoose((    State<List<JCTree>> s) -> unify(node,s).transform(treeState -> treeState.withResult(s.result().prepend(treeState.result()))));
  }
  return choice.transform(s -> s.withResult(s.result().reverse()));
}

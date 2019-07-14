void get_oracle_actions_travel(int root,List<Integer> heads,List<Integer> deprels,List<List<Integer>> tree,List<Action> actions){
  List<Integer> children=tree.get(root);
  int i;
  for (i=0; i < children.size() && children.get(i) < root; ++i) {
    get_oracle_actions_travel(children.get(i),heads,deprels,tree,actions);
  }
  actions.add(ActionFactory.make_shift());
  for (int j=i; j < children.size(); ++j) {
    int child=children.get(j);
    get_oracle_actions_travel(child,heads,deprels,tree,actions);
    actions.add(ActionFactory.make_right_arc(deprels.get(child)));
  }
  for (int j=i - 1; j >= 0; --j) {
    int child=children.get(j);
    actions.add(ActionFactory.make_left_arc(deprels.get(child)));
  }
}

void get_oracle_actions_onestep(List<Integer> heads,List<Integer> deprels,List<Integer> sigma,int[] beta,List<Integer> output,List<Action> actions){
  int top0=(sigma.size() > 0 ? sigma.get(sigma.size() - 1) : -1);
  int top1=(sigma.size() > 1 ? sigma.get(sigma.size() - 2) : -1);
  boolean all_descendents_reduced=true;
  if (top0 >= 0) {
    for (int i=0; i < heads.size(); ++i) {
      if (heads.get(i) == top0 && output.get(i) != top0) {
        all_descendents_reduced=false;
        break;
      }
    }
  }
  if (top1 >= 0 && heads.get(top1) == top0) {
    actions.add(ActionFactory.make_left_arc(deprels.get(top1)));
    output.set(top1,top0);
    sigma.remove(sigma.size() - 1);
    sigma.set(sigma.size() - 1,top0);
  }
 else   if (top1 >= 0 && heads.get(top0) == top1 && all_descendents_reduced) {
    actions.add(ActionFactory.make_right_arc(deprels.get(top0)));
    output.set(top0,top1);
    sigma.remove(sigma.size() - 1);
  }
 else   if (beta[0] < heads.size()) {
    actions.add(ActionFactory.make_shift());
    sigma.add(beta[0]);
    ++beta[0];
  }
}

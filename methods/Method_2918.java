/** 
 * ????
 * @param data ??
 * @param heads ?????????
 * @param deprels ?????????
 */
void predict(final Instance data,List<Integer> heads,List<String> deprels){
  Dependency dependency=new Dependency();
  List<Integer> cluster=new ArrayList<Integer>(), cluster4=new ArrayList<Integer>(), cluster6=new ArrayList<Integer>();
  transduce_instance_to_dependency(data,dependency,false);
  get_cluster_from_dependency(dependency,cluster4,cluster6,cluster);
  int L=data.forms.size();
  State[] states=new State[L * 2];
  for (int i=0; i < states.length; i++) {
    states[i]=new State();
  }
  states[0].copy(new State(dependency));
  system.transit(states[0],ActionFactory.make_shift(),states[1]);
  for (int step=1; step < L * 2 - 1; ++step) {
    List<Integer> attributes=new ArrayList<Integer>();
    if (use_cluster) {
      get_features(states[step],cluster4,cluster6,cluster,attributes);
    }
 else {
      get_features(states[step],attributes);
    }
    List<Double> scores=new ArrayList<Double>(system.number_of_transitions());
    classifier.score(attributes,scores);
    List<Action> possible_actions=new ArrayList<Action>();
    system.get_possible_actions(states[step],possible_actions);
    int best=-1;
    for (int j=0; j < possible_actions.size(); ++j) {
      int l=system.transform(possible_actions.get(j));
      if (best == -1 || scores.get(best) < scores.get(l)) {
        best=l;
      }
    }
    Action act=system.transform(best);
    system.transit(states[step],act,states[step + 1]);
  }
  for (int i=0; i < L; ++i) {
    heads.add(states[L * 2 - 1].heads.get(i));
    deprels.add(deprels_alphabet.labelOf(states[L * 2 - 1].deprels.get(i)));
  }
}

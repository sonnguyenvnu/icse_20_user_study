void transform(List<Action> actions,List<Integer> classes){
  classes.clear();
  for (int i=0; i < actions.size(); ++i) {
    classes.add(transform(actions.get(i)));
  }
}

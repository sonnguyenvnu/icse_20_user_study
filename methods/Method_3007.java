public static void commitAction(int action,int label,float score,ArrayList<Integer> dependencyRelations,Configuration newConfig){
  if (action == 0) {
    shift(newConfig.state);
    newConfig.addAction(0);
  }
 else   if (action == 1) {
    reduce(newConfig.state);
    newConfig.addAction(1);
  }
 else   if (action == 2) {
    rightArc(newConfig.state,label);
    newConfig.addAction(3 + label);
  }
 else   if (action == 3) {
    leftArc(newConfig.state,label);
    newConfig.addAction(3 + dependencyRelations.size() + label);
  }
 else   if (action == 4) {
    unShift(newConfig.state);
    newConfig.addAction(2);
  }
  newConfig.setScore(score);
}

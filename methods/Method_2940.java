/** 
 * ????
 * @param source ???
 * @param act ??
 * @param target ????
 */
void transit(State source,Action act,State target){
  int deprel=0;
  int[] deprel_inference=new int[]{deprel};
  if (ActionUtils.is_shift(act)) {
    target.shift(source);
  }
 else   if (ActionUtils.is_left_arc(act,deprel_inference)) {
    deprel=deprel_inference[0];
    target.left_arc(source,deprel);
  }
 else   if (ActionUtils.is_right_arc(act,deprel_inference)) {
    deprel=deprel_inference[0];
    target.right_arc(source,deprel);
  }
 else {
    System.err.printf("unknown transition in transit: %d-%d",act.name(),act.rel());
  }
}

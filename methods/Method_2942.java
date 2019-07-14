/** 
 * ???????id
 * @param act ??
 * @return ?????????id
 */
int transform(Action act){
  int deprel=0;
  int[] deprel_inference=new int[]{deprel};
  if (ActionUtils.is_shift(act)) {
    return 0;
  }
 else   if (ActionUtils.is_left_arc(act,deprel_inference)) {
    deprel=deprel_inference[0];
    return 1 + deprel;
  }
 else   if (ActionUtils.is_right_arc(act,deprel_inference)) {
    deprel=deprel_inference[0];
    return L + 1 + deprel;
  }
 else {
    System.err.printf("unknown transition in transform(Action): %d-%d",act.name(),act.rel());
  }
  return -1;
}

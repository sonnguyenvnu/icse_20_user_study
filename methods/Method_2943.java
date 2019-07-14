/** 
 * ????id???
 * @param act ?????????id
 * @return ??
 */
Action transform(int act){
  if (act == 0) {
    return ActionFactory.make_shift();
  }
 else   if (act < 1 + L) {
    return ActionFactory.make_left_arc(act - 1);
  }
 else   if (act < 1 + 2 * L) {
    return ActionFactory.make_right_arc(act - 1 - L);
  }
 else {
    System.err.printf("unknown transition in transform(int): %d",act);
  }
  return new Action();
}

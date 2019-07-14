/** 
 * ??????????????=shift | left | right + ?????????????????????????????
 * @param source ????
 * @param actions ??????
 */
void get_possible_actions(State source,List<Action> actions){
  if (0 == L || -1 == R) {
    System.err.println("decoder: not initialized, please check if the root dependency relation is correct set by --root.");
    return;
  }
  actions.clear();
  if (!source.buffer_empty()) {
    actions.add(ActionFactory.make_shift());
  }
  if (source.stack_size() == 2) {
    if (source.buffer_empty()) {
      actions.add(ActionFactory.make_right_arc(R));
    }
  }
 else   if (source.stack_size() > 2) {
    for (int l=0; l < L; ++l) {
      if (l == R) {
        continue;
      }
      actions.add(ActionFactory.make_left_arc(l));
      actions.add(ActionFactory.make_right_arc(l));
    }
  }
}

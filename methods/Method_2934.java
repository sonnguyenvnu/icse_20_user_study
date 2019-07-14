boolean left_arc(State source,int deprel){
  if (!source.can_left_arc()) {
    return false;
  }
  this.copy(source);
  stack.remove(stack.size() - 1);
  stack.set(stack.size() - 1,top0);
  heads.set(top1,top0);
  deprels.set(top1,deprel);
  if (-1 == left_most_child.get(top0)) {
    left_most_child.set(top0,top1);
  }
 else   if (top1 < left_most_child.get(top0)) {
    left_2nd_most_child.set(top0,left_most_child.get(top0));
    left_most_child.set(top0,top1);
  }
 else   if (top1 < left_2nd_most_child.get(top0)) {
    left_2nd_most_child.set(top0,top1);
  }
  nr_left_children.set(top0,nr_left_children.get(top0) + 1);
  refresh_stack_information();
  this.last_action=ActionFactory.make_left_arc(deprel);
  this.previous=source;
  return true;
}

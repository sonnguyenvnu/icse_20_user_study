/** 
 * ?????????
 * @param source ???
 */
void copy(State source){
  this.ref=source.ref;
  this.score=source.score;
  this.previous=source.previous;
  this.buffer=source.buffer;
  this.top0=source.top0;
  this.top1=source.top1;
  this.stack=source.stack;
  this.last_action=source.last_action;
  this.heads=source.heads;
  this.deprels=source.deprels;
  this.left_most_child=source.left_most_child;
  this.right_most_child=source.right_most_child;
  this.left_2nd_most_child=source.left_2nd_most_child;
  this.right_2nd_most_child=source.right_2nd_most_child;
  this.nr_left_children=source.nr_left_children;
  this.nr_right_children=source.nr_right_children;
}

@Override public StateConfigurer<S,E> end(S end){
  this.ends.add(end);
  state(end);
  return this;
}

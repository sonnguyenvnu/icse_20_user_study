boolean autoboxing(){
  return !annotations().containsKey(NoAutoboxing.class);
}

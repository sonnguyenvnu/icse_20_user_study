public boolean isEmpty(){
  return !violations.iterator().hasNext() && !hasErrors();
}

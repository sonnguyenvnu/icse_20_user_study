public URI forward(){
  if (!forward.isEmpty()) {
    backward.add(current);
    int size=forward.size();
    current=forward.remove(size - 1);
  }
  return current;
}

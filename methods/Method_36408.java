public Object getParent(){
  int size=size();
  if (size > 1) {
    return get(size - 2);
  }
  return null;
}

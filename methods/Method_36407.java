public Object getObject(){
  int size=size();
  if (size > 0) {
    return get(size - 1);
  }
  return null;
}

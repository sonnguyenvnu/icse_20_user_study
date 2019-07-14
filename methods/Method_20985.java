public Category root(){
  return isRoot() ? this : parent();
}

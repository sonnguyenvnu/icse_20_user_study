public boolean visit(Assert n){
  return traverseIntoNodes && dispatch(n);
}

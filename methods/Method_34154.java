private boolean elementsEqual(Object one,Object two){
  if (one == two) {
    return true;
  }
  Object targetOne=findTarget(one);
  Object targetTwo=findTarget(two);
  return targetOne == targetTwo;
}

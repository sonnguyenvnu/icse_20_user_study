private void boundsProblem(int index,String method){
  final String msg=String.format("The list size is %d. " + "You cannot %s() to element %d.",count,method,index);
  throw new ArrayIndexOutOfBoundsException(msg);
}

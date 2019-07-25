public Object calculate(){
  if (left instanceof MethodOpt) {
    return ((MethodOpt)left).calculate();
  }
  return null;
}

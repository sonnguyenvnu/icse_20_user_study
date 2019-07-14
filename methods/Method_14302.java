@Override public String checkArguments(Evaluable[] args){
  if (args.length != 3) {
    return ControlFunctionRegistry.getControlName(this) + " expects 3 arguments";
  }
  return null;
}

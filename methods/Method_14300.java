@Override public String checkArguments(Evaluable[] args){
  if (args.length != 5) {
    return ControlFunctionRegistry.getControlName(this) + " expects 5 arguments";
  }
 else   if (!(args[3] instanceof VariableExpr)) {
    return ControlFunctionRegistry.getControlName(this) + " expects third argument to be the element's variable name";
  }
  return null;
}

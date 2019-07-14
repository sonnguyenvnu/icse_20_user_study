@Override public String checkArguments(Evaluable[] args){
  if (args.length != 4) {
    return ControlFunctionRegistry.getControlName(this) + " expects 4 arguments";
  }
 else   if (!(args[1] instanceof VariableExpr)) {
    return ControlFunctionRegistry.getControlName(this) + " expects second argument to be the index's variable name";
  }
 else   if (!(args[2] instanceof VariableExpr)) {
    return ControlFunctionRegistry.getControlName(this) + " expects third argument to be the element's variable name";
  }
  return null;
}

@Override public String checkArguments(Evaluable[] args){
  if (args.length != 4) {
    return ControlFunctionRegistry.getControlName(this) + " expects 4 arguments";
  }
 else   if (!(args[1] instanceof VariableExpr)) {
    return ControlFunctionRegistry.getControlName(this) + " expects second argument to be a variable name";
  }
  return null;
}

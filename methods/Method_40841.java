public void invokeUncalled(FunType fun,Scope s){
  Scope funScope=new Scope(fun.env);
  if (fun.properties != null) {
    Declare.mergeType(fun.properties,funScope);
  }
  TypeChecker.self.callStack.add(fun);
  Value actual=fun.fun.body.typecheck(funScope);
  TypeChecker.self.callStack.remove(fun);
  Object retNode=fun.properties.lookupPropertyLocal(Constants.RETURN_ARROW,"type");
  if (retNode == null || !(retNode instanceof Node)) {
    _.abort("illegal return type: " + retNode);
    return;
  }
  Value expected=((Node)retNode).typecheck(funScope);
  if (!Type.subtype(actual,expected,true)) {
    _.abort(fun.fun,"type error in return value, expected: " + expected + ", actual: " + actual);
  }
}

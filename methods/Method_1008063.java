@Override void analyze(Locals locals){
  if (expected == null) {
    ref=null;
    actual=locals.getDefinition().getType("String");
    defPointer="S" + type + "." + call + ",0";
  }
 else {
    defPointer=null;
    try {
      if ("this".equals(type)) {
        Method interfaceMethod=expected.struct.getFunctionalMethod();
        if (interfaceMethod == null) {
          throw new IllegalArgumentException("Cannot convert function reference [" + type + "::" + call + "] " + "to [" + expected.name + "], not a functional interface");
        }
        Method delegateMethod=locals.getMethod(new MethodKey(call,interfaceMethod.arguments.size()));
        if (delegateMethod == null) {
          throw new IllegalArgumentException("Cannot convert function reference [" + type + "::" + call + "] " + "to [" + expected.name + "], function not found");
        }
        ref=new FunctionRef(expected,interfaceMethod,delegateMethod,0);
        for (int i=0; i < interfaceMethod.arguments.size(); ++i) {
          Definition.Type from=interfaceMethod.arguments.get(i);
          Definition.Type to=delegateMethod.arguments.get(i);
          locals.getDefinition().caster.getLegalCast(location,from,to,false,true);
        }
        if (interfaceMethod.rtn.equals(locals.getDefinition().voidType) == false) {
          locals.getDefinition().caster.getLegalCast(location,delegateMethod.rtn,interfaceMethod.rtn,false,true);
        }
      }
 else {
        ref=new FunctionRef(locals.getDefinition(),expected,type,call,0);
      }
    }
 catch (    IllegalArgumentException e) {
      throw createError(e);
    }
    actual=expected;
  }
}

/** 
 * Looks up  {@code type::call} from the whitelist, and returns a matching method.
 */
private static Definition.Method lookup(Definition definition,Definition.Type expected,String type,String call,boolean receiverCaptured){
  Method method=expected.struct.getFunctionalMethod();
  if (method == null) {
    throw new IllegalArgumentException("Cannot convert function reference [" + type + "::" + call + "] " + "to [" + expected.name + "], not a functional interface");
  }
  Definition.Struct struct=definition.getType(type).struct;
  final Definition.Method impl;
  if ("new".equals(call)) {
    impl=struct.constructors.get(new Definition.MethodKey("<init>",method.arguments.size()));
  }
 else {
    Definition.Method staticImpl=struct.staticMethods.get(new Definition.MethodKey(call,method.arguments.size()));
    if (staticImpl == null) {
      final int arity;
      if (receiverCaptured) {
        arity=method.arguments.size();
      }
 else {
        arity=method.arguments.size() - 1;
      }
      impl=struct.methods.get(new Definition.MethodKey(call,arity));
    }
 else {
      impl=staticImpl;
    }
  }
  if (impl == null) {
    throw new IllegalArgumentException("Unknown reference [" + type + "::" + call + "] matching " + "[" + expected + "]");
  }
  return impl;
}

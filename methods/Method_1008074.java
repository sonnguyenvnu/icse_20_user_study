@Override void analyze(Locals locals){
  getter=struct.methods.get(new Definition.MethodKey("get",1));
  setter=struct.methods.get(new Definition.MethodKey("put",2));
  if (getter != null && (getter.rtn.clazz == void.class || getter.arguments.size() != 1)) {
    throw createError(new IllegalArgumentException("Illegal map get shortcut for type [" + struct.name + "]."));
  }
  if (setter != null && setter.arguments.size() != 2) {
    throw createError(new IllegalArgumentException("Illegal map set shortcut for type [" + struct.name + "]."));
  }
  if (getter != null && setter != null && (!getter.arguments.get(0).equals(setter.arguments.get(0)) || !getter.rtn.equals(setter.arguments.get(1)))) {
    throw createError(new IllegalArgumentException("Shortcut argument types must match."));
  }
  if ((read || write) && (!read || getter != null) && (!write || setter != null)) {
    index.expected=setter != null ? setter.arguments.get(0) : getter.arguments.get(0);
    index.analyze(locals);
    index=index.cast(locals);
    actual=setter != null ? setter.arguments.get(1) : getter.rtn;
  }
 else {
    throw createError(new IllegalArgumentException("Illegal map shortcut for type [" + struct.name + "]."));
  }
}

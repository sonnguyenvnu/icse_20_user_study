@Override void analyze(Locals locals){
  if (getter != null && (getter.rtn.clazz == void.class || !getter.arguments.isEmpty())) {
    throw createError(new IllegalArgumentException("Illegal get shortcut on field [" + value + "] for type [" + type + "]."));
  }
  if (setter != null && (setter.rtn.clazz != void.class || setter.arguments.size() != 1)) {
    throw createError(new IllegalArgumentException("Illegal set shortcut on field [" + value + "] for type [" + type + "]."));
  }
  if (getter != null && setter != null && setter.arguments.get(0) != getter.rtn) {
    throw createError(new IllegalArgumentException("Shortcut argument types must match."));
  }
  if ((getter != null || setter != null) && (!read || getter != null) && (!write || setter != null)) {
    actual=setter != null ? setter.arguments.get(0) : getter.rtn;
  }
 else {
    throw createError(new IllegalArgumentException("Illegal shortcut on field [" + value + "] for type [" + type + "]."));
  }
}

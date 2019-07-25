public Object born(Object... args){
  if (args.length >= 1) {
    Object arg0=args[0];
    if (arg0 instanceof Number) {
      return Array.newInstance(eleType,((Number)arg0).intValue());
    }
  }
  throw Lang.makeThrow("array borning need length, arg0 should be number");
}

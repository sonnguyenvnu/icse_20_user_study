public Value typecheck(List<Value> args,Node location){
  Value v1=args.get(0);
  Value v2=args.get(1);
  if (v1 instanceof FloatType || v2 instanceof FloatType) {
    return new FloatType();
  }
  if (v1 instanceof IntType && v2 instanceof IntType) {
    return Type.INT;
  }
  _.abort(location,"incorrect argument types for -: " + v1 + ", " + v2);
  return null;
}

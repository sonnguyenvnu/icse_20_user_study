public Value typecheck(List<Value> args,Node location){
  Value v1=args.get(0);
  Value v2=args.get(1);
  if (!(v1 instanceof IntType || v1 instanceof FloatValue) || !(v2 instanceof IntType || v2 instanceof FloatValue)) {
    _.abort(location,"incorrect argument types for >: " + v1 + ", " + v2);
  }
  return Type.BOOL;
}

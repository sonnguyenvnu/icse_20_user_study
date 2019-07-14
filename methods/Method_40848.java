public Value typecheck(List<Value> args,Node location){
  Value v1=args.get(0);
  Value v2=args.get(1);
  if (v1 instanceof BoolType && v2 instanceof BoolType) {
    return Type.BOOL;
  }
  _.abort(location,"incorrect argument types for or: " + v1 + ", " + v2);
  return null;
}

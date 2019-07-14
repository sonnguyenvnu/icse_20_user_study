public Value typecheck(List<Value> args,Node location){
  Value v1=args.get(0);
  if (v1 instanceof BoolType) {
    return Type.BOOL;
  }
  _.abort(location,"incorrect argument type for not: " + v1);
  return null;
}

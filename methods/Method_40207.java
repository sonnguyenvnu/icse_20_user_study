private boolean subsumedInner(Type type1,Type type2,TypeStack typeStack){
  if (typeStack.contains(type1,type2)) {
    return true;
  }
  if (type1.isUnknownType() || type1 == Type.NONE || type1.equals(type2)) {
    return true;
  }
  if (type1 instanceof TupleType && type2 instanceof TupleType) {
    List<Type> elems1=((TupleType)type1).eltTypes;
    List<Type> elems2=((TupleType)type2).eltTypes;
    if (elems1.size() == elems2.size()) {
      typeStack.push(type1,type2);
      for (int i=0; i < elems1.size(); i++) {
        if (!subsumedInner(elems1.get(i),elems2.get(i),typeStack)) {
          typeStack.pop(type1,type2);
          return false;
        }
      }
    }
    return true;
  }
  if (type1 instanceof ListType && type2 instanceof ListType) {
    return subsumedInner(((ListType)type1).toTupleType(),((ListType)type2).toTupleType(),typeStack);
  }
  return false;
}

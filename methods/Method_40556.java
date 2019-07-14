public static boolean subtypeOf(Type type1,Type type2){
  if (typeStack.contains(type1,type2)) {
    return true;
  }
 else   if (type1 instanceof TupleType && type2 instanceof TupleType) {
    List<Type> elems1=((TupleType)type1).getElementTypes();
    List<Type> elems2=((TupleType)type2).getElementTypes();
    if (elems1.size() == elems2.size()) {
      typeStack.push(type1,type2);
      for (int i=0; i < elems1.size(); i++) {
        if (!elems2.get(i).isUnknownType() && !elems1.get(i).equals(elems2.get(i))) {
          typeStack.pop(type1,type2);
          return false;
        }
      }
      typeStack.pop(type1,type2);
      return true;
    }
 else {
      return false;
    }
  }
 else {
    return false;
  }
}

/** 
 * Computes the concrete output type corresponding to a given abstract output type.
 * @param abstractOutputType an abstract output type.
 * @param numStack the size of the input stack, used to resolve abstract output types ofSTACK_KIND kind.
 * @return the concrete output type corresponding to 'abstractOutputType'.
 */
private int getConcreteOutputType(final int abstractOutputType,final int numStack){
  int dim=abstractOutputType & DIM_MASK;
  int kind=abstractOutputType & KIND_MASK;
  if (kind == LOCAL_KIND) {
    int concreteOutputType=dim + inputLocals[abstractOutputType & VALUE_MASK];
    if ((abstractOutputType & TOP_IF_LONG_OR_DOUBLE_FLAG) != 0 && (concreteOutputType == LONG || concreteOutputType == DOUBLE)) {
      concreteOutputType=TOP;
    }
    return concreteOutputType;
  }
 else   if (kind == STACK_KIND) {
    int concreteOutputType=dim + inputStack[numStack - (abstractOutputType & VALUE_MASK)];
    if ((abstractOutputType & TOP_IF_LONG_OR_DOUBLE_FLAG) != 0 && (concreteOutputType == LONG || concreteOutputType == DOUBLE)) {
      concreteOutputType=TOP;
    }
    return concreteOutputType;
  }
 else {
    return abstractOutputType;
  }
}

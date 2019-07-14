private static boolean isTimeUnit(Symbol receiverSymbol,VisitorState state){
  return isSameType(state.getTypeFromString("java.util.concurrent.TimeUnit"),receiverSymbol.type,state);
}

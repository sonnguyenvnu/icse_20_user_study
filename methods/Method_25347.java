private static boolean hasAttribute(Symbol symbol,String name,VisitorState state){
  Symbol annotation=state.getSymbolFromString(name);
  return annotation != null && symbol.attribute(annotation) != null;
}

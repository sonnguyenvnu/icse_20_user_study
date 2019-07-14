private static Symbol getOnlyMember(VisitorState state,Type type,String name){
  return getOnlyElement(type.tsym.members().getSymbolsByName(state.getName(name)));
}

private static TreeSet<Type> typeSet(VisitorState state){
  return new TreeSet<>((t1,t2) -> state.getTypes().isSameType(t1,t2) ? 0 : t1.toString().compareTo(t2.toString()));
}

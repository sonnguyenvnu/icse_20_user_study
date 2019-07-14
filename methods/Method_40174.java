/** 
 * Create an outline for a symbol table.
 * @param state the file state
 * @param path  the file for which we're building the outline
 * @return a list of entries constituting the outline
 */
@NotNull public List<Entry> generate(@NotNull State state,@NotNull String path){
  List<Entry> result=new ArrayList<>();
  Set<Binding> entries=new TreeSet<>();
  for (  Binding b : state.values()) {
    if (!b.isSynthetic() && !b.isBuiltin() && path.equals(b.getFile())) {
      entries.add(b);
    }
  }
  for (  Binding nb : entries) {
    List<Entry> kids=null;
    if (nb.kind == Binding.Kind.CLASS) {
      Type realType=nb.type;
      if (realType instanceof UnionType) {
        for (        Type t : ((UnionType)realType).types) {
          if (t instanceof ClassType) {
            realType=t;
            break;
          }
        }
      }
      kids=generate(realType.table,path);
    }
    Entry kid=kids != null ? new Branch() : new Leaf();
    kid.setOffset(nb.start);
    kid.setQname(nb.qname);
    kid.setKind(nb.kind);
    if (kids != null) {
      kid.setChildren(kids);
    }
    result.add(kid);
  }
  return result;
}

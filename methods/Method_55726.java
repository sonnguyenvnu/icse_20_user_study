protected static Layout __union(int packAlignment,int alignas,Member... members){
  List<Member> union=new ArrayList<>(members.length);
  int size=0;
  int alignment=alignas;
  for (  Member m : members) {
    size=max(size,m.size);
    alignment=max(alignment,m.getAlignment(packAlignment));
    m.offset=0;
    union.add(m);
    if (m instanceof Layout) {
      addNestedMembers(m,union,0);
    }
  }
  return new Layout(size,alignment,alignas != 0,union.toArray(new Member[0]));
}

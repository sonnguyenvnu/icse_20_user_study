protected static Layout __struct(int packAlignment,int alignas,Member... members){
  List<Member> struct=new ArrayList<>(members.length);
  int size=0;
  int alignment=alignas;
  for (  Member m : members) {
    int memberAlignment=m.getAlignment(packAlignment);
    m.offset=align(size,memberAlignment);
    size=m.offset + m.size;
    alignment=max(alignment,memberAlignment);
    struct.add(m);
    if (m instanceof Layout) {
      addNestedMembers(m,struct,m.offset);
    }
  }
  size=align(size,alignment);
  return new Layout(size,alignment,alignas != 0,struct.toArray(new Member[0]));
}

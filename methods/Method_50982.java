public void addUse(MemberNode use){
  if (uses == null) {
    uses=new ArrayList<>(1);
  }
  if (!uses.contains(use)) {
    uses.add(use);
  }
}

protected void printMember(MemberNode memberNode){
  System.out.println("\t\t(" + memberNode.getUses().size() + ", " + memberNode.getUsers().size() + ") " + memberNode.toStringLong());
}

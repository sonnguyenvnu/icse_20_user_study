@Override public void visitNestMember(final String nestMember){
  if (nestMemberClasses == null) {
    nestMemberClasses=new ByteVector();
  }
  ++numberOfNestMemberClasses;
  nestMemberClasses.putShort(symbolTable.addConstantClass(nestMember).index);
}

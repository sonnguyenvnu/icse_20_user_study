private static Kind getKind(Tree tree){
  Kind kind=tree.getKind();
  return kind == Kind.IDENTIFIER ? Kind.MEMBER_SELECT : kind;
}

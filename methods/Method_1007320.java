public static ASTList make(ASTree e1,ASTree e2,ASTree e3){
  return new ASTList(e1,new ASTList(e2,new ASTList(e3)));
}

public static Stmnt make(int op,ASTree op1,ASTree op2,ASTree op3){
  return new Stmnt(op,op1,new ASTList(op2,new ASTList(op3)));
}

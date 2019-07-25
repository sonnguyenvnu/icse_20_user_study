final public SyntaxTreeNode Case() throws ParseException {
  SyntaxTreeNode tn;
  Token t;
  bpa("CASE Expression");
  t=jj_consume_token(CASE);
  addHeir(new SyntaxTreeNode(mn,t));
  tn=CaseArm();
  addHeir(tn);
  label_44:   while (true) {
    if (caseSep() && (getToken(2).kind != OTHER)) {
      ;
    }
 else {
      break label_44;
    }
    t=jj_consume_token(CASESEP);
    addHeir(new SyntaxTreeNode(mn,t));
    tn=CaseArm();
    addHeir(tn);
  }
  if (caseSep()) {
    t=jj_consume_token(CASESEP);
    addHeir(new SyntaxTreeNode(mn,t));
    tn=OtherArm();
    addHeir(tn);
  }
 else {
    ;
  }
  SyntaxTreeNode sn[]=getLastHeirs();
  epa();
{
    if (true)     return new SyntaxTreeNode(mn,N_Case,sn);
  }
  throw new Error("Missing return statement in function");
}

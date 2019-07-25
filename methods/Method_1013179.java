final public SyntaxTreeNode Instantiation() throws ParseException {
  SyntaxTreeNode tn;
  Token t;
  bpa("NonLocalInstance");
  t=jj_consume_token(INSTANCE);
  addHeir(new SyntaxTreeNode(mn,t));
  expecting="Module identifier";
  t=getToken(1);
  if (isFieldNameToken(t))   t.kind=IDENTIFIER;
  tn=Identifier();
  addDependency(tn.image);
  addHeir(tn);
  expecting="WITH or another definition.";
switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
case WITH:
    t=jj_consume_token(WITH);
  addHeir(new SyntaxTreeNode(mn,t));
expecting=emptyString;
tn=Substitution();
addHeir(tn);
expecting=emptyString;
label_12: while (true) {
if (jj_2_12(3)) {
;
}
 else {
break label_12;
}
t=jj_consume_token(COMMA);
addHeir(new SyntaxTreeNode(mn,t));
expecting=emptyString;
tn=Substitution();
addHeir(tn);
expecting=emptyString;
}
break;
default :
jj_la1[34]=jj_gen;
;
}
SyntaxTreeNode sn[]=getLastHeirs();
epa();
{
if (true) return new SyntaxTreeNode(mn,N_NonLocalInstance,sn);
}
throw new Error("Missing return statement in function");
}

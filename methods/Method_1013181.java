/** 
 * Theorem ::= ( <THEOREM> | <PROPOSITION> )                                 ( Identifier <DEF> )? ( AssumeProve | Expression )         * Produces a Theorem node tn with tn.zero containing 2 or 4 nodes,          depending on whether or not the "Identifier <DEF>" is present.           
 */
final public SyntaxTreeNode Theorem() throws ParseException {
  SyntaxTreeNode tn;
  Token t;
  bpa("Theorem");
  expecting="THEOREM, PROPOSITION";
switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
case THEOREM:
    t=jj_consume_token(THEOREM);
  break;
case PROPOSITION:
t=jj_consume_token(PROPOSITION);
break;
default :
jj_la1[54]=jj_gen;
jj_consume_token(-1);
throw new ParseException();
}
addHeir(new SyntaxTreeNode(mn,t));
expecting="Identifier, Assume-Prove or Expression";
if (jj_2_23(2)) {
tn=Identifier();
addHeir(tn);
expecting="==";
t=jj_consume_token(DEF);
addHeir(new SyntaxTreeNode(mn,t));
}
 else {
;
}
belchDEF();
if (jj_2_24(3)) {
if (getToken(1).kind == ASSUME || getToken(1).kind == BOXASSUME) {
}
 else {
jj_consume_token(-1);
throw new ParseException();
}
tn=AssumeProve();
}
 else if (jj_2_25(1)) {
tn=Expression();
}
 else {
jj_consume_token(-1);
throw new ParseException();
}
addHeir(tn);
if (beginsProof(getToken(1))) {
tn=Proof();
addHeir(tn);
}
 else {
;
}
SyntaxTreeNode sn[]=getLastHeirs();
epa();
{
if (true) return new SyntaxTreeNode(mn,N_Theorem,sn);
}
throw new Error("Missing return statement in function");
}

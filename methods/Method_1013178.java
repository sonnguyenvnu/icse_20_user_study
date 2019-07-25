final public SyntaxTreeNode Body() throws ParseException {
  SyntaxTreeNode tn, sn[];
  Token t;
  bpa("Module body");
  expecting="LOCAL, INSTANCE, PROOF, ASSUMPTION, THEOREM, " + "RECURSIVE, declaration, or definition";
  label_3:   while (true) {
    if (jj_2_1(1)) {
      ;
    }
 else {
      break label_3;
    }
switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
case SEPARATOR:
      t=jj_consume_token(SEPARATOR);
    tn=new SyntaxTreeNode(mn,t);
  break;
case VARIABLE:
tn=VariableDeclaration();
break;
case CONSTANT:
tn=ParamDeclaration();
break;
default :
jj_la1[10]=jj_gen;
if (jj_2_2(2)) {
tn=OperatorOrFunctionDefinition();
}
 else {
switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
case RECURSIVE:
tn=Recursive();
break;
default :
jj_la1[11]=jj_gen;
if (jj_2_3(2)) {
tn=Instance();
}
 else if (jj_2_4(2)) {
tn=Assumption();
}
 else if (jj_2_5(2)) {
tn=Theorem();
}
 else {
switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
case _BM1:
case _BM2:
case _BM0:
tn=Module();
belchDEF();
break;
default :
jj_la1[12]=jj_gen;
if ((getToken(1).kind == USE && getToken(2).kind != ONLY) || (getToken(1).kind == HIDE)) {
tn=UseOrHideOrBy();
}
 else {
jj_consume_token(-1);
throw new ParseException();
}
}
}
}
}
}
addHeir(tn);
}
sn=getLastHeirs();
epa();
{
if (true) return new SyntaxTreeNode(mn,N_Body,sn);
}
throw new Error("Missing return statement in function");
}

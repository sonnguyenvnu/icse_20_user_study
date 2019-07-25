double eval(ExprState es){
  Expr left=null;
  Expr right=null;
  if (children != null && children.size() > 0) {
    left=children.firstElement();
    if (children.size() == 2)     right=children.lastElement();
  }
switch (type) {
case E_ADD:
    return left.eval(es) + right.eval(es);
case E_SUB:
  return left.eval(es) - right.eval(es);
case E_MUL:
return left.eval(es) * right.eval(es);
case E_DIV:
return left.eval(es) / right.eval(es);
case E_POW:
return java.lang.Math.pow(left.eval(es),right.eval(es));
case E_UMINUS:
return -left.eval(es);
case E_VAL:
return value;
case E_T:
return es.t;
case E_SIN:
return java.lang.Math.sin(left.eval(es));
case E_COS:
return java.lang.Math.cos(left.eval(es));
case E_ABS:
return java.lang.Math.abs(left.eval(es));
case E_EXP:
return java.lang.Math.exp(left.eval(es));
case E_LOG:
return java.lang.Math.log(left.eval(es));
case E_SQRT:
return java.lang.Math.sqrt(left.eval(es));
case E_TAN:
return java.lang.Math.tan(left.eval(es));
case E_MIN:
{
int i;
double x=left.eval(es);
for (i=1; i < children.size(); i++) x=Math.min(x,children.get(i).eval(es));
return x;
}
case E_MAX:
{
int i;
double x=left.eval(es);
for (i=1; i < children.size(); i++) x=Math.max(x,children.get(i).eval(es));
return x;
}
case E_CLAMP:
return Math.min(Math.max(left.eval(es),children.get(1).eval(es)),children.get(2).eval(es));
case E_STEP:
{
double x=left.eval(es);
if (right == null) return (x < 0) ? 0 : 1;
return (x > right.eval(es)) ? 0 : (x < 0) ? 0 : 1;
}
case E_SELECT:
{
double x=left.eval(es);
return children.get(x > 0 ? 2 : 1).eval(es);
}
case E_TRIANGLE:
{
double x=posmod(left.eval(es),Math.PI * 2) / Math.PI;
return (x < 1) ? -1 + x * 2 : 3 - x * 2;
}
case E_SAWTOOTH:
{
double x=posmod(left.eval(es),Math.PI * 2) / Math.PI;
return x - 1;
}
case E_MOD:
return left.eval(es) % right.eval(es);
case E_PWL:
return pwl(es,children);
default :
if (type >= E_A) return es.values[type - E_A];
CirSim.console("unknown\n");
}
return 0;
}

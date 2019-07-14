static JCTree.JCExpression parseString(String guardedByString,Context context){
  JavacParser parser=ParserFactory.instance(context).newParser(guardedByString,false,true,false);
  JCTree.JCExpression exp;
  try {
    exp=parser.parseExpression();
  }
 catch (  Throwable e) {
    throw new IllegalGuardedBy(e.getMessage());
  }
  int len=(parser.getEndPos(exp) - exp.getStartPosition());
  if (len != guardedByString.length()) {
    throw new IllegalGuardedBy("Didn't parse entire string.");
  }
  return exp;
}

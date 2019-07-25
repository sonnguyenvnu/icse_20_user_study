public final boolean match(OpApplNode test,ModuleNode mn){
  SymbolNode odn=test.getOperator();
  return odn.getArity() == 0;
}

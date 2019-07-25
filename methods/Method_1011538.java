@Override public void transform(){
  preprocess();
  replaceAssignmentsWithBinaryOperations();
  replaceConstructors();
  replaceThis();
  replaceSupers();
  replaceLowLevelVariableReferences();
  replaceClassExpressions();
  replaceLocalMemberReferences();
  boolean finished=false;
  int count=0;
  while (!(finished)) {
    finished=replaceInstanceMethodCalls();
    finished&=replaceInternalPartialInstanceMethodCalls();
    finished&=replaceStaticMethodCalls();
    finished&=replaceInternalStaticMethodCalls();
    finished&=replaceArrayOperations();
    finished&=replaceFieldReferenceOperations();
    finished&=replaceInternalPartialFieldReferences();
    finished&=replaceStaticFieldReferences();
    finished&=replaceInternalStaticFieldReferences();
    finished&=replaceBinaryOperations();
    finished&=replaceTernaryOperators();
    finished&=replaceNotExpressions();
    finished&=replaceAssignments();
    finished&=replaceLocalVariableDeclarations();
    finished&=replaceForeachVariable();
    finished&=replaceCasts();
    if (count++ > 100) {
      break;
    }
  }
  replaceInstanceofs();
  postprocess();
}

public EcmascriptNode<?> getStatement(int index){
  int statementIndex=index;
  if (!isDefault()) {
    statementIndex++;
  }
  return (EcmascriptNode<?>)jjtGetChild(statementIndex);
}

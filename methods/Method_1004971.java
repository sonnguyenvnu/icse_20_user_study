private void validate(final OperationChain<?> operationChain,final NamedOperationDetail namedOperationDetail) throws OperationException {
  for (  final Operation op : operationChain.getOperations()) {
    if (op instanceof NamedOperation) {
      throw new OperationException("NamedOperations can not be nested within NamedOperations");
    }
  }
  if (null != namedOperationDetail.getParameters()) {
    String operationString=namedOperationDetail.getOperations();
    for (    final Map.Entry<String,ParameterDetail> parameterDetail : namedOperationDetail.getParameters().entrySet()) {
      String varName="${" + parameterDetail.getKey() + "}";
      if (!operationString.contains(varName)) {
        throw new OperationException("Parameter specified in NamedOperation doesn't occur in OperationChain string for " + varName);
      }
    }
  }
}

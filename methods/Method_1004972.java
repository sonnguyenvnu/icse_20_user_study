private void validate(final View namedViewWithDefaultParams,final NamedViewDetail namedViewDetail) throws OperationException {
  if (namedViewWithDefaultParams instanceof NamedView) {
    throw new OperationException("NamedView can not be nested within NamedView");
  }
  if (null != namedViewDetail.getParameters()) {
    String viewString=namedViewDetail.getView();
    for (    final Map.Entry<String,ViewParameterDetail> parameterDetail : namedViewDetail.getParameters().entrySet()) {
      String varName="${" + parameterDetail.getKey() + "}";
      if (!viewString.contains(varName)) {
        throw new OperationException("Parameter specified in NamedView doesn't occur in View string for " + varName);
      }
    }
  }
}

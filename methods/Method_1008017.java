@Override public Object run(){
  try {
    return expression.evaluate(functionValuesArray);
  }
 catch (  Exception exception) {
    throw new GeneralScriptException("Error evaluating " + expression,exception);
  }
}

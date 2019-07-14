/** 
 * Evaluates the given XPath expression against the current tree.
 * @param xPath XPath expression to be evaluated
 * @param evaluator object which requests the evaluation
 */
public void evaluateXPathExpression(String xPath,Object evaluator) throws ParseException, JaxenException {
  try {
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("xPath=" + xPath);
      LOGGER.finest("evaluator=" + evaluator);
    }
    XPath xpath=new BaseXPath(xPath,new DocumentNavigator());
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("xpath=" + xpath);
      LOGGER.finest("rootNode=" + rootNode);
    }
    try {
      evaluationResults=xpath.selectNodes(rootNode);
    }
 catch (    Exception e) {
      LOGGER.finest("selectNodes problem:");
      e.printStackTrace(System.err);
    }
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("evaluationResults=" + evaluationResults);
    }
    fireViewerModelEvent(new ViewerModelEvent(evaluator,ViewerModelEvent.PATH_EXPRESSION_EVALUATED));
  }
 catch (  JaxenException je) {
    je.printStackTrace(System.err);
    throw je;
  }
}

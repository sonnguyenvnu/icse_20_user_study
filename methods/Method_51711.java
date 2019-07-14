/** 
 * Appends the given fragment to the XPath expression.
 * @param pathFragment fragment to be added
 * @param appender object that is trying to append the fragment
 */
public void appendToXPathExpression(String pathFragment,Object appender){
  fireViewerModelEvent(new ViewerModelEvent(appender,ViewerModelEvent.PATH_EXPRESSION_APPENDED,pathFragment));
}

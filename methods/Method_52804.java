/** 
 * Return minimal XPathHandler to cope with Jaxen XPath Rules.
 */
@Override public XPathHandler getXPathHandler(){
  return new DefaultASTXPathHandler();
}

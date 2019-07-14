@Override public boolean hasDescendantMatchingXPath(final String xpathString){
  try {
    return !findChildNodesWithXPath(xpathString).isEmpty();
  }
 catch (  final JaxenException e) {
    throw new RuntimeException("XPath expression " + xpathString + " failed: " + e.getLocalizedMessage(),e);
  }
}

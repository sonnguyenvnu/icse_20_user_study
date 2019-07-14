/** 
 * @param navigator the navigator which is required to be non-null if the {@link #initializationStatus} is PARTIAL.
 * @throws JaxenException
 */
@SuppressWarnings("unchecked") private void initializeExpressionIfStatusIsNoneOrPartial(final Navigator navigator) throws JaxenException {
  if (initializationStatus == InitializationStatus.FULL) {
    return;
  }
  if (initializationStatus == InitializationStatus.PARTIAL && navigator == null) {
    LOG.severe("XPathRule is not initialized because no navigator was provided. " + "Please make sure to implement getXPathHandler in the handler of the language. " + "See also AbstractLanguageVersionHandler.");
    return;
  }
  initializeXPathExpression(navigator);
}

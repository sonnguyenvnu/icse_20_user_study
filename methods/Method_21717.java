protected void updateRequestWithCollapse(Select select,SearchRequestBuilder request) throws SqlParseException {
  for (  Hint hint : select.getHints()) {
    if (hint.getType() == HintType.COLLAPSE && hint.getParams() != null && 0 < hint.getParams().length) {
      try (XContentParser parser=JsonXContent.jsonXContent.createParser(NamedXContentRegistry.EMPTY,LoggingDeprecationHandler.INSTANCE,hint.getParams()[0].toString())){
        request.setCollapse(CollapseBuilder.fromXContent(parser));
      }
 catch (      IOException e) {
        throw new SqlParseException("could not parse collapse hint: " + e.getMessage());
      }
    }
  }
}

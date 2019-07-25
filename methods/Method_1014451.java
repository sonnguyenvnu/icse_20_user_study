/** 
 * Transforms the input <code>source</code> by JSonPath expression.
 * @param function JsonPath expression
 * @param source String which contains JSON
 * @throws TransformationException If the JsonPath expression is invalid, a {@link InvalidPathException} is thrown,which is encapsulated in a  {@link TransformationException}.
 */
@Override public @Nullable String transform(String jsonPathExpression,String source) throws TransformationException {
  if (jsonPathExpression == null || source == null) {
    throw new TransformationException("the given parameters 'JSonPath' and 'source' must not be null");
  }
  logger.debug("about to transform '{}' by the function '{}'",source,jsonPathExpression);
  try {
    Object transformationResult=JsonPath.read(source,jsonPathExpression);
    logger.debug("transformation resulted in '{}'",transformationResult);
    if (transformationResult == null) {
      return null;
    }
 else     if (transformationResult instanceof List) {
      return flattenList((List<?>)transformationResult);
    }
 else {
      return transformationResult.toString();
    }
  }
 catch (  PathNotFoundException e) {
    throw new TransformationException("Invalid path '" + jsonPathExpression + "' in '" + source + "'");
  }
catch (  InvalidPathException|InvalidJsonException e) {
    throw new TransformationException("An error occurred while transforming JSON expression.",e);
  }
}

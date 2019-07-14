/** 
 * Subclass should override this method to modify the JavaParser as needed.
 */
protected JavaParser createJavaParser(Reader source) throws ParseException {
  parser=new JavaParser(new JavaCharStream(source));
  String suppressMarker=getParserOptions().getSuppressMarker();
  if (suppressMarker != null) {
    parser.setSuppressMarker(suppressMarker);
  }
  return parser;
}

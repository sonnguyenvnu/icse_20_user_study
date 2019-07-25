/** 
 * @deprecated this method will be removed in a future version; use ShapeParser.parse instead 
 */
@Deprecated public static ShapeBuilder parse(XContentParser parser) throws IOException {
  return ShapeParser.parse(parser);
}

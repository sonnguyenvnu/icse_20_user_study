public static void nested(ParseContext context,ObjectMapper.Nested nested){
  ParseContext.Document nestedDoc=context.doc();
  ParseContext.Document parentDoc=nestedDoc.getParent();
  if (nested.isIncludeInParent()) {
    addFields(nestedDoc,parentDoc);
  }
  if (nested.isIncludeInRoot()) {
    ParseContext.Document rootDoc=context.rootDoc();
    if (!nested.isIncludeInParent() || parentDoc != rootDoc) {
      addFields(nestedDoc,rootDoc);
    }
  }
}

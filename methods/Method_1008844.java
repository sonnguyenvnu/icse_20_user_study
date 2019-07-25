protected static void process(HeaderPart header,FieldVisitor fldVisitor){
  if ((header != null) && (!header.getContent().isEmpty())) {
    TraversalUtil.visit(header.getContent(),fldVisitor);
  }
}

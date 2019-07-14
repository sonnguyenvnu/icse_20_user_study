@Override public final void visitAttribute(final Attribute attribute){
  attribute.nextAttribute=firstAttribute;
  firstAttribute=attribute;
}

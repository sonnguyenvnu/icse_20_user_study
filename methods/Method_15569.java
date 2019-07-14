@Override public AbstractVerifier<T> setVisitor(Visitor<T> visitor){
  this.visitor=visitor;
  this.visitorId=visitor == null ? null : visitor.getId();
  return this;
}

public Document _root(){
  Document doc=this;
  while (doc._parent != null) {
    doc=doc._parent;
  }
  return doc == null ? this : doc;
}

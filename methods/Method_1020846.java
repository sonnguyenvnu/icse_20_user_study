public boolean persist(String field,Object value){
  Document parent=doc._parent;
  if (parent != null) {
  }
 else {
    doc.runCallbacks(Callbacks.Callback.before_update);
    DBCollection collection=(DBCollection)ReflectHelper.staticMethod(doc.getClass(),"collection");
    Map inc=map("field","value");
    doc.runCallbacks(Callbacks.Callback.after_update);
  }
  return true;
}

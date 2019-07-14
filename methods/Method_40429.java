/** 
 * Look for the name in the current scope.  If found, and its qname is a valid binding in the graph, record a reference.
 */
private void checkForReference(int offset,String qname){
  Binding nb;
  if (qname.indexOf('.') == -1) {
    nb=scope.lookup(qname);
    if (nb == null) {
      nb=Indexer.idx.globaltable.lookup(qname);
    }
  }
 else {
    nb=Indexer.idx.lookupFirstBinding(qname);
  }
  if (nb != null) {
    linker.processRef(new Ref(file,offset,qname),nb);
  }
}

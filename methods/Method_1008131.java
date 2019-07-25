@Override public int advance(int target) throws IOException {
  doc=_innerIter.advance(target);
  try {
    if (doc != NO_MORE_DOCS) {
      if (match(doc)) {
        return doc;
      }
 else {
        while ((doc=_innerIter.nextDoc()) != NO_MORE_DOCS) {
          if (match(doc)) {
            return doc;
          }
        }
        return doc;
      }
    }
  }
 catch (  CollectionTerminatedException e) {
    return doc=NO_MORE_DOCS;
  }
  return doc;
}

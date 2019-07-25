/** 
 * Used to accept or ignore a search hit. Ignored search hits will be excluded from the bulk request. It is also where we fail on invalid search hits, like when the document has no source but it's required.
 */
protected boolean accept(ScrollableHitSource.Hit doc){
  if (doc.getSource() == null) {
    throw new IllegalArgumentException("[" + doc.getIndex() + "][" + doc.getType() + "][" + doc.getId() + "] didn't store _source");
  }
  return true;
}

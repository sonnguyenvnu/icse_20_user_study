/** 
 * Iterates collection.
 */
protected void iterateCollection(final Collection collection,final int from,final int count,final PageContext pageContext) throws JspException {
  JspFragment body=getJspBody();
  Iterator iter=collection.iterator();
  int i=0;
  int to=calculateTo(from,count,collection.size());
  while (i < to) {
    Object item=iter.next();
    if (i >= from) {
      if (status != null) {
        iteratorStatus.next(!iter.hasNext());
      }
      TagUtil.setScopeAttribute(var,item,scope,pageContext);
      TagUtil.invokeBody(body);
    }
    i++;
  }
}

/** 
 * Iterates arrays.
 */
protected void iterateArray(final Object[] array,final int from,final int count,final PageContext pageContext) throws JspException {
  JspFragment body=getJspBody();
  int len=array.length;
  int to=calculateTo(from,count,len);
  int last=to - 1;
  for (int i=from; i < to; i++) {
    Object item=array[i];
    if (status != null) {
      iteratorStatus.next(i == last);
    }
    TagUtil.setScopeAttribute(var,item,scope,pageContext);
    TagUtil.invokeBody(body);
  }
}

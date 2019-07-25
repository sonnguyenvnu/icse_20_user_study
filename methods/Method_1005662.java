/** 
 * {@inheritDoc} 
 */
@Override public void annotate(String msg){
  if (annotations == null) {
    return;
  }
  endAnnotation();
  annotations.add(new Annotation(cursor,msg));
}

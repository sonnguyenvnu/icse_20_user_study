/** 
 * {@inheritDoc} 
 */
@Override public void annotate(int amt,String msg){
  if (annotations == null) {
    return;
  }
  endAnnotation();
  int asz=annotations.size();
  int lastEnd=(asz == 0) ? 0 : annotations.get(asz - 1).getEnd();
  int startAt;
  if (lastEnd <= cursor) {
    startAt=cursor;
  }
 else {
    startAt=lastEnd;
  }
  annotations.add(new Annotation(startAt,startAt + amt,msg));
}

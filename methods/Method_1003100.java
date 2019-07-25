@Override public boolean next(){
  if (beforeFirst) {
    beforeFirst=false;
    current=start;
  }
 else {
    current+=step;
  }
  currentRow=session.createRow(new Value[]{ValueLong.get(current)},1);
  return step > 0 ? current <= end : current >= end;
}

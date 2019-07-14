public void setCheckmarks(@NonNull List<Checkmark> checkmarks){
  this.checkmarks=checkmarks;
  maxValue=1.0;
  for (  Checkmark c : checkmarks)   maxValue=Math.max(maxValue,c.getValue());
  maxValue=Math.ceil(maxValue / 1000 * 1.05) * 1000;
  postInvalidate();
}

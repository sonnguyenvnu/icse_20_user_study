public void setSpansCount(int count){
  count+=mSpanCountOverride;
  mSpansOverride=new Object[count];
  mSpanDataOverride=new int[count * 3];
  num=mSpanCountOverride;
  mSpanCountOverride=count;
  try {
    mSpansField.set(this,mSpansOverride);
    mSpanDataField.set(this,mSpanDataOverride);
    mSpanCountField.set(this,mSpanCountOverride);
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
}

public void setSpanLight(Object what,int start,int end,int flags){
  mSpansOverride[num]=what;
  mSpanDataOverride[num * 3]=start;
  mSpanDataOverride[num * 3 + 1]=end;
  mSpanDataOverride[num * 3 + 2]=flags;
  num++;
}

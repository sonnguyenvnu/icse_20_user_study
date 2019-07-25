protected void slide(float percent){
  if (percent < 0)   percent=0;
 else   if (percent > 1)   percent=1;
  currPercent=percent;
}

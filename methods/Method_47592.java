public int getIsCurrentlyAmOrPm(){
  if (mCurrentHoursOfDay < 12) {
    return AM;
  }
 else   if (mCurrentHoursOfDay < 24) {
    return PM;
  }
  return -1;
}

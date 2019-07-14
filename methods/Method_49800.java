public float getImplicitDuration(){
  float dur=-1.0F;
  if (ENDSYNC_LAST.equals(getEndSync())) {
    NodeList children=getTimeChildren();
    for (int i=0; i < children.getLength(); ++i) {
      ElementTime child=(ElementTime)children.item(i);
      TimeList endTimeList=child.getEnd();
      for (int j=0; j < endTimeList.getLength(); ++j) {
        Time endTime=endTimeList.item(j);
        if (endTime.getTimeType() == Time.SMIL_TIME_INDEFINITE) {
          return -1.0F;
        }
        if (endTime.getResolved()) {
          float end=(float)endTime.getResolvedOffset();
          dur=(end > dur) ? end : dur;
        }
      }
    }
  }
  return dur;
}

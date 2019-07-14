@Override public void onPositionDiscontinuity(EventTime eventTime,@Player.DiscontinuityReason int reason){
  logd(eventTime,"positionDiscontinuity",getDiscontinuityReasonString(reason));
}

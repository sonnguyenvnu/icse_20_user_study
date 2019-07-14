private void isNeedToAdjust(Canvas canvas,Adjuster.Opportunity currentOpportunity){
  for (int i=0; i < adjusterList.size(); i++) {
    Adjuster adjuster=adjusterList.get(i);
    if (currentOpportunity == adjuster.getOpportunity()) {
      if (adjuster.getType() == Adjuster.TYPE_SYSTEM) {
        adjuster.adjust(this,canvas);
      }
 else       if (autoAdjust) {
        adjuster.adjust(this,canvas);
      }
    }
  }
}

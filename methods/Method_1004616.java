/** 
 * Overriding the "standard" milestone behavior (where we display something at each milestone) Instead, we populate a line drawer that will connect the steps
 */
@Override public void draw(Canvas pCanvas,MilestoneStep pStep){
  if (mFirst) {
    mFirst=false;
  }
 else {
    mLineDrawer.add(pStep.getX(),pStep.getY());
  }
  mLineDrawer.add(pStep.getX(),pStep.getY());
}

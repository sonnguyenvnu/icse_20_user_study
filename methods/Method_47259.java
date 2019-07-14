private void setSelectedPage(int now){
  if (now == currentPage)   return;
  pageChanging=true;
  previousPage=currentPage;
  currentPage=now;
  final int steps=Math.abs(now - previousPage);
  if (steps > 1) {
    if (now > previousPage) {
      for (int i=0; i < steps; i++) {
        setJoiningFraction(previousPage + i,1f);
      }
    }
 else {
      for (int i=-1; i > -steps; i--) {
        setJoiningFraction(previousPage + i,1f);
      }
    }
  }
  moveAnimation=createMoveSelectedAnimator(dotCenterX[now],previousPage,now,steps);
  moveAnimation.start();
}

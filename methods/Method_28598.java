/** 
 * ????
 * @param autoTurningTime ??????
 * @return
 */
public ConvenientBanner startTurning(long autoTurningTime){
  if (autoTurningTime < 0)   return this;
  if (turning) {
    stopTurning();
  }
  canTurn=true;
  this.autoTurningTime=autoTurningTime;
  turning=true;
  postDelayed(adSwitchTask,autoTurningTime);
  return this;
}

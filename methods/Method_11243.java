/** 
 * ??
 */
public void reset(){
  setStop(true);
  progress=0;
  isFinish=false;
  isStop=false;
  progressColor=loadingColor;
  progressText="";
  flickerLeft=-flikerBitmap.getWidth();
  initPgBimap();
}

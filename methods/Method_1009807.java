/** 
 * ????
 */
public void start(){
  if (mBarInfoList == null || mBarInfoList.size() == 0) {
    Log.e(TAG,"????????????");
    return;
  }
  mAnimRate=0;
  if (mAnim.isRunning()) {
    mAnim.cancel();
  }
  mAnim.start();
}

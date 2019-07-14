/** 
 * Post a runnable to the next frame to run pending item animations. Only the first such request will be posted, governed by the mPostedAnimatorRunner flag.
 */
void postAnimationRunner(){
  if (!mPostedAnimatorRunner && mIsAttached) {
    ViewCompat.postOnAnimation(this,mItemAnimatorRunner);
    mPostedAnimatorRunner=true;
  }
}

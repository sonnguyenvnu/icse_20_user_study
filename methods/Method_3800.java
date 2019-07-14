/** 
 * Starts dragging or swiping the given View. Call with null if you want to clear it.
 * @param selected    The ViewHolder to drag or swipe. Can be null if you want to cancel thecurrent action, but may not be null if actionState is ACTION_STATE_DRAG.
 * @param actionState The type of action
 */
@SuppressWarnings("WeakerAccess") void select(@Nullable ViewHolder selected,int actionState){
  if (selected == mSelected && actionState == mActionState) {
    return;
  }
  mDragScrollStartTimeInMs=Long.MIN_VALUE;
  final int prevActionState=mActionState;
  endRecoverAnimation(selected,true);
  mActionState=actionState;
  if (actionState == ACTION_STATE_DRAG) {
    if (selected == null) {
      throw new IllegalArgumentException("Must pass a ViewHolder when dragging");
    }
    mOverdrawChild=selected.itemView;
    addChildDrawingOrderCallback();
  }
  int actionStateMask=(1 << (DIRECTION_FLAG_COUNT + DIRECTION_FLAG_COUNT * actionState)) - 1;
  boolean preventLayout=false;
  if (mSelected != null) {
    final ViewHolder prevSelected=mSelected;
    if (prevSelected.itemView.getParent() != null) {
      final int swipeDir=prevActionState == ACTION_STATE_DRAG ? 0 : swipeIfNecessary(prevSelected);
      releaseVelocityTracker();
      final float targetTranslateX, targetTranslateY;
      int animationType;
switch (swipeDir) {
case LEFT:
case RIGHT:
case START:
case END:
        targetTranslateY=0;
      targetTranslateX=Math.signum(mDx) * mRecyclerView.getWidth();
    break;
case UP:
case DOWN:
  targetTranslateX=0;
targetTranslateY=Math.signum(mDy) * mRecyclerView.getHeight();
break;
default :
targetTranslateX=0;
targetTranslateY=0;
}
if (prevActionState == ACTION_STATE_DRAG) {
animationType=ANIMATION_TYPE_DRAG;
}
 else if (swipeDir > 0) {
animationType=ANIMATION_TYPE_SWIPE_SUCCESS;
}
 else {
animationType=ANIMATION_TYPE_SWIPE_CANCEL;
}
getSelectedDxDy(mTmpPosition);
final float currentTranslateX=mTmpPosition[0];
final float currentTranslateY=mTmpPosition[1];
final RecoverAnimation rv=new RecoverAnimation(prevSelected,animationType,prevActionState,currentTranslateX,currentTranslateY,targetTranslateX,targetTranslateY){
@Override public void onAnimationEnd(Animator animation){
super.onAnimationEnd(animation);
if (this.mOverridden) {
return;
}
if (swipeDir <= 0) {
mCallback.clearView(mRecyclerView,prevSelected);
}
 else {
mPendingCleanup.add(prevSelected.itemView);
mIsPendingCleanup=true;
if (swipeDir > 0) {
postDispatchSwipe(this,swipeDir);
}
}
if (mOverdrawChild == prevSelected.itemView) {
removeChildDrawingOrderCallbackIfNecessary(prevSelected.itemView);
}
}
}
;
final long duration=mCallback.getAnimationDuration(mRecyclerView,animationType,targetTranslateX - currentTranslateX,targetTranslateY - currentTranslateY);
rv.setDuration(duration);
mRecoverAnimations.add(rv);
rv.start();
preventLayout=true;
}
 else {
removeChildDrawingOrderCallbackIfNecessary(prevSelected.itemView);
mCallback.clearView(mRecyclerView,prevSelected);
}
mSelected=null;
}
if (selected != null) {
mSelectedFlags=(mCallback.getAbsoluteMovementFlags(mRecyclerView,selected) & actionStateMask) >> (mActionState * DIRECTION_FLAG_COUNT);
mSelectedStartX=selected.itemView.getLeft();
mSelectedStartY=selected.itemView.getTop();
mSelected=selected;
if (actionState == ACTION_STATE_DRAG) {
mSelected.itemView.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
}
}
final ViewParent rvParent=mRecyclerView.getParent();
if (rvParent != null) {
rvParent.requestDisallowInterceptTouchEvent(mSelected != null);
}
if (!preventLayout) {
mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
}
mCallback.onSelectedChanged(mSelected,mActionState);
mRecyclerView.invalidate();
}

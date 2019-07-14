@Override public void onStopNestedScroll(View child){
  if (Math.abs(totalDrag) >= dragDismissDistance) {
    dispatchDismissCallback();
  }
 else {
    animate().translationY(0f).scaleX(1f).scaleY(1f).setDuration(200L).setInterpolator(new FastOutSlowInInterpolator()).setListener(null).start();
    totalDrag=0;
    draggingDown=draggingUp=false;
    dispatchDragCallback(0f,0f,0f,0f);
  }
}

/** 
 * Refresh the view visibility
 */
private void refresh(){
  if ((null != mRoom) && (null != mSession)) {
    List<Widget> activeWidgets=WidgetsManager.getSharedInstance().getActiveWebviewWidgets(mSession,mRoom);
    Widget firstWidget=null;
    if ((activeWidgets.size() != mActiveWidgets.size()) || !mActiveWidgets.containsAll(activeWidgets)) {
      mActiveWidgets=activeWidgets;
      if (1 == mActiveWidgets.size()) {
        firstWidget=mActiveWidgets.get(0);
        mWidgetTypeTextView.setText(firstWidget.getHumanName());
      }
 else       if (mActiveWidgets.size() > 1) {
        mWidgetTypeTextView.setText(mContext.getResources().getQuantityString(R.plurals.active_widgets,mActiveWidgets.size(),mActiveWidgets.size()));
      }
      if (null != mUpdateListener) {
        try {
          mUpdateListener.onActiveWidgetsListUpdate();
        }
 catch (        Exception e) {
          Log.e(LOG_TAG,"## refresh() : onActiveWidgetUpdate failed " + e.getMessage(),e);
        }
      }
    }
    setVisibility((mActiveWidgets.size() > 0) ? View.VISIBLE : View.GONE);
    mCloseWidgetIcon.setVisibility(((null != firstWidget) && (null == WidgetsManager.getSharedInstance().checkWidgetPermission(mSession,mRoom))) ? View.VISIBLE : View.GONE);
  }
}

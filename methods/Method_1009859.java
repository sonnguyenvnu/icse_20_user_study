/** 
 * Refresh the view visibility
 */
public void refresh(){
  if ((null != mRoom) && (null != mSession)) {
    List<Widget> mActiveWidgets=WidgetsManager.getSharedInstance().getActiveJitsiWidgets(mSession,mRoom);
    Widget widget=mActiveWidgets.isEmpty() ? null : mActiveWidgets.get(0);
    if (mActiveWidget != widget) {
      mActiveWidget=widget;
      if (null != mCallClickListener) {
        try {
          mCallClickListener.onActiveWidgetUpdate();
        }
 catch (        Exception e) {
          Log.e(LOG_TAG,"## refresh() : onActiveWidgetUpdate failed " + e.getMessage(),e);
        }
      }
    }
    IMXCall call=mSession.mCallsManager.getCallWithRoomId(mRoom.getRoomId());
    setVisibility(((!MXCallsManager.isCallInProgress(call) && mRoom.isOngoingConferenceCall()) || (null != mActiveWidget)) ? View.VISIBLE : View.GONE);
    mCloseWidgetIcon.setVisibility(((null != mActiveWidget) && (null == WidgetsManager.getSharedInstance().checkWidgetPermission(mSession,mRoom))) ? View.VISIBLE : View.GONE);
  }
}

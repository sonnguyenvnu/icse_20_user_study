/** 
 * Unregister a pusher.
 * @param pusher   the pusher.
 * @param callback the asynchronous callback
 */
public void unregister(final MXSession session,final Pusher pusher,final ApiCallback<Void> callback){
  session.getPushersRestClient().removeHttpPusher(pusher.pushkey,pusher.appId,pusher.profileTag,pusher.lang,pusher.appDisplayName,pusher.deviceDisplayName,pusher.data.get("url"),new SimpleApiCallback<Void>(callback){
    @Override public void onSuccess(    Void info){
      refreshPushersList(new ArrayList<>(Matrix.getInstance(mContext).getSessions()),callback);
    }
    @Override public void onMatrixError(    MatrixError e){
      if (e.mStatus == 404) {
        onSuccess(null);
        return;
      }
      super.onMatrixError(e);
    }
  }
);
}

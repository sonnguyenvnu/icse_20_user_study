@Override protected void reloadAll(){
  Log.d(TAG,"reloadAll >>> ");
  HttpRequest.getUser(APIJSONApplication.getInstance().getCurrentUserId(),0,new OnHttpResponseListener(){
    @Override public void onHttpResponse(    int requestCode,    String resultJson,    Exception e){
      Log.d(TAG,"reloadAll >>> HttpRequest.getUser.onHttpResponse >>  saveCurrentUser >>");
      APIJSONApplication.getInstance().saveCurrentUser(new JSONResponse(resultJson).getObject(User.class));
      runUiThread(new Runnable(){
        @Override public void run(){
          sendBroadcast(new Intent(ActionUtil.ACTION_USER_CHANGED).putExtra(INTENT_ID,APIJSONApplication.getInstance().getCurrentUserId()).putExtra(ActionUtil.INTENT_USER,APIJSONApplication.getInstance().getCurrentUser()));
        }
      }
);
    }
  }
);
}

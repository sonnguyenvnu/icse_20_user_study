private void jump(String uri){
  if (DemoConstant.JUMP_WITH_REQUEST.equals(uri)) {
    new DefaultUriRequest(this,uri).activityRequestCode(100).putExtra(TestUriRequestActivity.INTENT_TEST_INT,1).putExtra(TestUriRequestActivity.INTENT_TEST_STR,"str").overridePendingTransition(R.anim.enter_activity,R.anim.exit_activity).onComplete(new OnCompleteListener(){
      @Override public void onSuccess(      @NonNull UriRequest request){
        ToastUtils.showToast(request.getContext(),"????");
      }
      @Override public void onError(      @NonNull UriRequest request,      int resultCode){
      }
    }
).start();
  }
 else {
    Router.startUri(this,uri);
  }
}

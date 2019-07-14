private void postToast(final int resId){
  mServiceHandler.post(new Runnable(){
    @Override public void run(){
      ToastUtils.show(resId,SaveImageService.this);
    }
  }
);
}

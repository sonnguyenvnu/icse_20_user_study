@Override public void intercept(final Chain chain) throws Exception {
  PermissionsUtil.with(chain.request().getRawContext()).request(Manifest.permission.CAMERA).execute(new PermissionsCallback(){
    @Override public void onResult(    boolean granted){
      if (granted) {
        try {
          chain.proceed(chain.request());
        }
 catch (        Exception e) {
          chain.callback().onError(new Exception("fail to request camera permision"));
        }
      }
 else {
        chain.callback().onError(new Exception("fail to request camera permision"));
      }
    }
  }
);
}

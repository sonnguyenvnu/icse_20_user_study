public static void check(FragmentActivity activity){
  new RxPermissions(activity).request(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>(){
    @Override public void accept(    Boolean aBoolean) throws Exception {
      if (!aBoolean) {
        ToastUtil.showToast("?????????????????????????????????");
      }
    }
  }
);
}

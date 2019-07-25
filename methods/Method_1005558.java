@Override public void intercept(@NonNull final UriRequest request,@NonNull final UriCallback callback){
  ILocationService locationService=DemoServiceManager.getLocationService();
  if (locationService.hasLocation()) {
    callback.onNext();
    return;
  }
  final Context context=request.getContext();
  if (!(context instanceof Activity) || ((Activity)context).isFinishing()) {
    callback.onNext();
    return;
  }
  final ProgressDialog dialog=DialogUtils.showProgress(context,"???...");
  locationService.startLocation(new ILocationService.LocationListener(){
    @Override public void onSuccess(){
      ToastUtils.showToast(context,"????");
      DialogUtils.dismiss(dialog);
      callback.onNext();
    }
    @Override public void onFailure(){
      ToastUtils.showToast(context,"????");
      DialogUtils.dismiss(dialog);
      callback.onComplete(CustomUriResult.CODE_LOCATION_FAILURE);
    }
  }
);
}

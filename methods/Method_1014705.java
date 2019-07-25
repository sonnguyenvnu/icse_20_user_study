@RequiresApi(api=Build.VERSION_CODES.M) private boolean rationale(final Activity activity){
  boolean isRationale=false;
  if (mOnRationaleListener != null) {
    for (    String permission : mPermissionsRequest) {
      if (activity.shouldShowRequestPermissionRationale(permission)) {
        getPermissionsStatus(activity);
        mOnRationaleListener.rationale(new OnRationaleListener.ShouldRequest(){
          @Override public void again(          boolean again){
            if (again) {
              startPermissionActivity();
            }
 else {
              requestCallback();
            }
          }
        }
);
        isRationale=true;
        break;
      }
    }
    mOnRationaleListener=null;
  }
  return isRationale;
}

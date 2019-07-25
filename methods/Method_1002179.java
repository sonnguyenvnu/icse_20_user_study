/** 
 * Shows the prompt for the given origin. When the user clicks on one of the buttons, the supplied callback is be called.
 */
public void show(String origin,GeolocationPermissions.Callback callback){
  mOrigin=origin;
  mCallback=callback;
  Uri uri=Uri.parse(mOrigin);
  setMessage("http".equals(uri.getScheme()) ? mOrigin.substring(7) : mOrigin);
  mRemember.setChecked(true);
  setVisibility(View.VISIBLE);
}

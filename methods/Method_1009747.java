/** 
 * Call to setup the controller.
 */
public void start(){
  mVibrator=(Vibrator)mContext.getSystemService(Service.VIBRATOR_SERVICE);
  mIsGloballyEnabled=checkGlobalSetting(mContext);
  Uri uri=Settings.System.getUriFor(Settings.System.HAPTIC_FEEDBACK_ENABLED);
  mContext.getContentResolver().registerContentObserver(uri,false,mContentObserver);
}

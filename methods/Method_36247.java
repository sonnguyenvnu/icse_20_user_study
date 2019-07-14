static void fixLeakCanary696(Context context){
  if (!isEmui()) {
    Log.w(TAG,"not emui");
    return;
  }
  try {
    Class clazz=Class.forName("android.gestureboost.GestureBoostManager");
    Log.w(TAG,"clazz " + clazz);
    Field _sGestureBoostManager=clazz.getDeclaredField("sGestureBoostManager");
    _sGestureBoostManager.setAccessible(true);
    Field _mContext=clazz.getDeclaredField("mContext");
    _mContext.setAccessible(true);
    Object sGestureBoostManager=_sGestureBoostManager.get(null);
    if (sGestureBoostManager != null) {
      _mContext.set(sGestureBoostManager,context);
    }
  }
 catch (  Exception ignored) {
    ignored.printStackTrace();
  }
}

public static void initFastClickAndVibrate(Context mContext,OnDoListener onRxSimple){
  if (RxTool.isFastClick(RxConstants.FAST_CLICK_TIME)) {
    RxToast.normal("???????");
    return;
  }
 else {
    RxVibrateTool.vibrateOnce(mContext,RxConstants.VIBRATE_TIME);
    onRxSimple.doSomething();
  }
}

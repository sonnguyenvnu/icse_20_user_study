@Override public double getTrimRatio(MemoryTrimType trimType){
switch (trimType) {
case OnCloseToDalvikHeapLimit:
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      return MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio();
    }
 else {
      return 0;
    }
case OnAppBackgrounded:
case OnSystemMemoryCriticallyLowWhileAppInForeground:
case OnSystemLowMemoryWhileAppInForeground:
case OnSystemLowMemoryWhileAppInBackground:
  return 1;
default :
FLog.wtf(TAG,"unknown trim type: %s",trimType);
return 0;
}
}

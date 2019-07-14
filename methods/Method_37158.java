public void onExposure(@NonNull View targetView,@NonNull BaseCell cell,int type){
  if (optimizedMode) {
    defaultExposureCell(targetView,cell,type);
  }
 else {
    if (mOnExposureMethods.isEmpty() || mOnTraceMethods.isEmpty()) {
      Method[] methods=this.getClass().getMethods();
      findTraceMethods(methods);
      findExposureMethods(methods);
    }
    List<Class<?>> classes=lookupCellTypes(targetView.getClass());
    for (    Class clz : classes) {
      if (clz.equals(View.class)) {
        continue;
      }
      if (mOnExposureMethods.containsKey(clz)) {
        OnTraceMethod onTraceMethod=mOnExposureMethods.get(clz);
        try {
          if (onTraceMethod.paramLength == 3) {
            onTraceMethod.method.invoke(this,targetView,cell,type);
            return;
          }
        }
 catch (        Exception e) {
          LogUtils.e(TAG,"Invoke onExposure method error: " + Log.getStackTraceString(e),e);
        }
      }
    }
    defaultExposureCell(targetView,cell,type);
  }
}

/** 
 * Handler exposure event on item
 * @param targetView the view that trigger the exposure event, not the view respond the cell!
 * @param cell       the corresponding cell
 * @param type       exposure event type, defined by developer.
 */
public void onTrace(@NonNull View targetView,@NonNull BaseCell cell,int type){
  if (optimizedMode) {
    defaultTrace(targetView,cell,type);
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
      if (mOnTraceMethods.containsKey(clz)) {
        OnTraceMethod onTraceMethod=mOnTraceMethods.get(clz);
        try {
          if (onTraceMethod.paramLength == 3) {
            onTraceMethod.method.invoke(this,targetView,cell,type);
            return;
          }
        }
 catch (        Exception e) {
          LogUtils.e(TAG,"Invoke Trace method error: " + Log.getStackTraceString(e),e);
        }
      }
    }
    defaultTrace(targetView,cell,type);
  }
}

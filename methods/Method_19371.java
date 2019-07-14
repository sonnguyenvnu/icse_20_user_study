public static void setRenderInfoToViewMapping(View view,Object renderInfoSectionDebugInfo){
  if (sViewToRenderInfo == null) {
    sViewToRenderInfo=new WeakHashMap<>();
  }
  sViewToRenderInfo.put(view,new WeakReference<Object>(renderInfoSectionDebugInfo));
}

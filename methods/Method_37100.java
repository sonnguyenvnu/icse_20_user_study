private void postMountView(BaseCell cell,View view){
  if (!cell.mIsExposed && cell.serviceManager != null) {
    ExposureSupport exposureSupport=cell.serviceManager.getService(ExposureSupport.class);
    if (exposureSupport != null) {
      cell.mIsExposed=true;
      exposureSupport.onExposure(view,cell,cell.pos);
    }
  }
  if (view instanceof ITangramViewLifeCycle) {
    ((ITangramViewLifeCycle)view).postBindView(cell);
  }
 else {
    if (postBindMap.get(cell) != null) {
      try {
        postBindMap.get(cell).invoke(view,cell);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
}

/** 
 * Don't change card's mCells in binding process! <p/>
 */
public void onBindCell(int offset,int position,boolean showFromEnd){
  if (!mIsExposed && serviceManager != null) {
    ExposureSupport exposureSupport=serviceManager.getService(ExposureSupport.class);
    if (exposureSupport != null) {
      mIsExposed=true;
      exposureSupport.onExposure(this,offset,position);
    }
  }
}

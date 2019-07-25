/** 
 * ???????
 */
public void reset(){
  previewTrack=true;
  enable3DPose=false;
  enableROIDetect=false;
  roiRatio=0.8f;
  enable106Points=true;
  isBackCamera=false;
  enableFaceProperty=false;
  enableMultiFace=true;
  minFaceSize=200;
  detectInterval=25;
  trackMode=Facepp.FaceppConfig.DETECTION_MODE_TRACKING_SMOOTH;
  trackerCallback=null;
}

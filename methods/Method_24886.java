/** 
 * Parse the sketch size and set the internal sizeInfo variable 
 */
public SurfaceInfo initSketchSize(String code,boolean sizeWarning) throws SketchException {
  sizeInfo=parseSketchSize(code,sizeWarning);
  return sizeInfo;
}

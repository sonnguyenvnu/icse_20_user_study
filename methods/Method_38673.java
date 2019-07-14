/** 
 * Resets JSON parser, so it can be reused.
 */
protected void reset(){
  this.ndx=0;
  this.textLen=0;
  this.path=new Path();
  this.notFirstObject=false;
  if (useAltPaths) {
    path.altPath=new Path();
  }
  if (classMetadataName != null) {
    mapToBean=createMapToBean(classMetadataName);
  }
}

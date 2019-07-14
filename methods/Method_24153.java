/** 
 * Same as glFrustum(), except that it wipes out (rather than multiplies against) the current perspective matrix. <P> Implementation based on the explanation in the OpenGL blue book.
 */
@Override public void frustum(float left,float right,float bottom,float top,float znear,float zfar){
  flush();
  cameraFOV=2 * (float)Math.atan2(top,znear);
  cameraAspect=left / bottom;
  cameraNear=znear;
  cameraFar=zfar;
  float n2=2 * znear;
  float w=right - left;
  float h=top - bottom;
  float d=zfar - znear;
  projection.set(n2 / w,0,(right + left) / w,0,0,-n2 / h,(top + bottom) / h,0,0,0,-(zfar + znear) / d,-(n2 * zfar) / d,0,0,-1,0);
  updateProjmodelview();
}

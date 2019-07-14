/** 
 * Set the number of segments to use when drawing a Catmull-Rom curve, and setting the s parameter, which defines how tightly the curve fits to each vertex. Catmull-Rom curves are actually a subset of this curve type where the s is set to zero. <P> (This function is not optimized, since it's not expected to be called all that often. there are many juicy and obvious opimizations in here, but it's probably better to keep the code more readable)
 */
protected void curveInit(){
  if (curveDrawMatrix == null) {
    curveBasisMatrix=new PMatrix3D();
    curveDrawMatrix=new PMatrix3D();
    curveInited=true;
  }
  float s=curveTightness;
  curveBasisMatrix.set((s - 1) / 2f,(s + 3) / 2f,(-3 - s) / 2f,(1 - s) / 2f,(1 - s),(-5 - s) / 2f,(s + 2),(s - 1) / 2f,(s - 1) / 2f,0,(1 - s) / 2f,0,0,1,0,0);
  splineForward(curveDetail,curveDrawMatrix);
  if (bezierBasisInverse == null) {
    bezierBasisInverse=bezierBasisMatrix.get();
    bezierBasisInverse.invert();
    curveToBezierMatrix=new PMatrix3D();
  }
  curveToBezierMatrix.set(curveBasisMatrix);
  curveToBezierMatrix.preApply(bezierBasisInverse);
  curveDrawMatrix.apply(curveBasisMatrix);
}

/** 
 * ( begin auto-generated from curveTangent.xml ) Calculates the tangent of a point on a curve. There's a good definition of <em><a href="http://en.wikipedia.org/wiki/Tangent" target="new">tangent</em> on Wikipedia</a>. ( end auto-generated ) <h3>Advanced</h3> Code thanks to Dave Bollinger (Bug #715)
 * @webref shape:curves
 * @param a coordinate of first point on the curve
 * @param b coordinate of first control point
 * @param c coordinate of second control point
 * @param d coordinate of second point on the curve
 * @param t value between 0 and 1
 * @see PGraphics#curve(float,float,float,float,float,float,float,float,float,float,float,float)
 * @see PGraphics#curveVertex(float,float)
 * @see PGraphics#curvePoint(float,float,float,float,float)
 * @see PGraphics#bezierTangent(float,float,float,float,float)
 */
public float curveTangent(float a,float b,float c,float d,float t){
  curveInitCheck();
  float tt3=t * t * 3;
  float t2=t * 2;
  PMatrix3D cb=curveBasisMatrix;
  return (a * (tt3 * cb.m00 + t2 * cb.m10 + cb.m20) + b * (tt3 * cb.m01 + t2 * cb.m11 + cb.m21) + c * (tt3 * cb.m02 + t2 * cb.m12 + cb.m22) + d * (tt3 * cb.m03 + t2 * cb.m13 + cb.m23));
}

/** 
 * ( begin auto-generated from lerp.xml ) Calculates a number between two numbers at a specific increment. The <b>amt</b> parameter is the amount to interpolate between the two values where 0.0 equal to the first point, 0.1 is very near the first point, 0.5 is half-way in between, etc. The lerp function is convenient for creating motion along a straight path and for drawing dotted lines. ( end auto-generated )
 * @webref math:calculation
 * @param start first value
 * @param stop second value
 * @param amt float between 0.0 and 1.0
 * @see PGraphics#curvePoint(float,float,float,float,float)
 * @see PGraphics#bezierPoint(float,float,float,float,float)
 * @see PVector#lerp(PVector,float)
 * @see PGraphics#lerpColor(int,int,float)
 */
static public final float lerp(float start,float stop,float amt){
  return start + (stop - start) * amt;
}

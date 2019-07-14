/** 
 * Setup forward-differencing matrix to be used for speedy curve rendering. It's based on using a specific number of curve segments and just doing incremental adds for each vertex of the segment, rather than running the mathematically expensive cubic equation.
 * @param segments number of curve segments to use when drawing
 * @param matrix target object for the new matrix
 */
protected void splineForward(int segments,PMatrix3D matrix){
  float f=1.0f / segments;
  float ff=f * f;
  float fff=ff * f;
  matrix.set(0,0,0,1,fff,ff,f,0,6 * fff,2 * ff,0,0,6 * fff,0,0,0);
}

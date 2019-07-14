/** 
 * @param cz the z-coordinate of the control point
 * @param z3 the z-coordinate of the anchor point
 */
public void quadraticVertex(float cx,float cy,float cz,float x3,float y3,float z3){
  float[] prev=vertices[vertexCount - 1];
  float x1=prev[X];
  float y1=prev[Y];
  float z1=prev[Z];
  bezierVertex(x1 + ((cx - x1) * 2 / 3.0f),y1 + ((cy - y1) * 2 / 3.0f),z1 + ((cz - z1) * 2 / 3.0f),x3 + ((cx - x3) * 2 / 3.0f),y3 + ((cy - y3) * 2 / 3.0f),z3 + ((cz - z3) * 2 / 3.0f),x3,y3,z3);
}

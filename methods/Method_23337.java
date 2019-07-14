/** 
 * @webref shape:vertex
 * @param cx the x-coordinate of the control point
 * @param cy the y-coordinate of the control point
 * @param x3 the x-coordinate of the anchor point
 * @param y3 the y-coordinate of the anchor point
 * @see PGraphics#curveVertex(float,float,float)
 * @see PGraphics#vertex(float,float,float,float,float)
 * @see PGraphics#bezierVertex(float,float,float,float,float,float)
 * @see PGraphics#bezier(float,float,float,float,float,float,float,float,float,float,float,float)
 */
public void quadraticVertex(float cx,float cy,float x3,float y3){
  float[] prev=vertices[vertexCount - 1];
  float x1=prev[X];
  float y1=prev[Y];
  bezierVertex(x1 + ((cx - x1) * 2 / 3.0f),y1 + ((cy - y1) * 2 / 3.0f),x3 + ((cx - x3) * 2 / 3.0f),y3 + ((cy - y3) * 2 / 3.0f),x3,y3);
}

/** 
 * ( begin auto-generated from beginShape.xml ) Using the <b>beginShape()</b> and <b>endShape()</b> functions allow creating more complex forms. <b>beginShape()</b> begins recording vertices for a shape and <b>endShape()</b> stops recording. The value of the <b>MODE</b> parameter tells it which types of shapes to create from the provided vertices. With no mode specified, the shape can be any irregular polygon. The parameters available for beginShape() are POINTS, LINES, TRIANGLES, TRIANGLE_FAN, TRIANGLE_STRIP, QUADS, and QUAD_STRIP. After calling the <b>beginShape()</b> function, a series of <b>vertex()</b> commands must follow. To stop drawing the shape, call <b>endShape()</b>. The <b>vertex()</b> function with two parameters specifies a position in 2D and the <b>vertex()</b> function with three parameters specifies a position in 3D. Each shape will be outlined with the current stroke color and filled with the fill color. <br/> <br/> Transformations such as <b>translate()</b>, <b>rotate()</b>, and <b>scale()</b> do not work within <b>beginShape()</b>. It is also not possible to use other shapes, such as <b>ellipse()</b> or <b>rect()</b> within <b>beginShape()</b>. <br/> <br/> The P3D renderer settings allow <b>stroke()</b> and <b>fill()</b> settings to be altered per-vertex, however the default P2D renderer does not. Settings such as <b>strokeWeight()</b>, <b>strokeCap()</b>, and <b>strokeJoin()</b> cannot be changed while inside a <b>beginShape()</b>/<b>endShape()</b> block with any renderer. ( end auto-generated )
 * @webref shape:vertex
 * @param kind Either POINTS, LINES, TRIANGLES, TRIANGLE_FAN, TRIANGLE_STRIP, QUADS, or QUAD_STRIP
 * @see PShape
 * @see PGraphics#endShape()
 * @see PGraphics#vertex(float,float,float,float,float)
 * @see PGraphics#curveVertex(float,float,float)
 * @see PGraphics#bezierVertex(float,float,float,float,float,float,float,float,float)
 */
public void beginShape(int kind){
  shape=kind;
}

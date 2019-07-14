/** 
 * ( begin auto-generated from loop.xml ) Causes Processing to continuously execute the code within <b>draw()</b>. If <b>noLoop()</b> is called, the code in <b>draw()</b> stops executing. ( end auto-generated )
 * @webref structure
 * @usage web_application
 * @see PApplet#noLoop()
 * @see PApplet#redraw()
 * @see PApplet#draw()
 */
synchronized public void loop(){
  if (!looping) {
    looping=true;
  }
}

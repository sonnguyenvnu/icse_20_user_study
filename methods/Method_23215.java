/** 
 * ( begin auto-generated from noLoop.xml ) Stops Processing from continuously executing the code within <b>draw()</b>. If <b>loop()</b> is called, the code in <b>draw()</b> begin to run continuously again. If using <b>noLoop()</b> in <b>setup()</b>, it should be the last line inside the block. <br/> <br/> When <b>noLoop()</b> is used, it's not possible to manipulate or access the screen inside event handling functions such as <b>mousePressed()</b> or <b>keyPressed()</b>. Instead, use those functions to call <b>redraw()</b> or <b>loop()</b>, which will run <b>draw()</b>, which can update the screen properly. This means that when noLoop() has been called, no drawing can happen, and functions like saveFrame() or loadPixels() may not be used. <br/> <br/> Note that if the sketch is resized, <b>redraw()</b> will be called to update the sketch, even after <b>noLoop()</b> has been specified. Otherwise, the sketch would enter an odd state until <b>loop()</b> was called. ( end auto-generated )
 * @webref structure
 * @usage web_application
 * @see PApplet#loop()
 * @see PApplet#redraw()
 * @see PApplet#draw()
 */
synchronized public void noLoop(){
  if (looping) {
    looping=false;
  }
}

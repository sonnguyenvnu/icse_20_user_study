public void handleDraw(){
  if (g == null)   return;
  if (!looping && !redraw)   return;
  if (insideDraw) {
    System.err.println("handleDraw() called before finishing");
    System.exit(1);
  }
  insideDraw=true;
  g.beginDraw();
  if (recorder != null) {
    recorder.beginDraw();
  }
  long now=System.nanoTime();
  if (frameCount == 0) {
    setup();
  }
 else {
{
      double frameTimeSecs=(now - frameRateLastNanos) / 1e9;
      double avgFrameTimeSecs=1.0 / frameRate;
      final double alpha=0.05;
      avgFrameTimeSecs=(1.0 - alpha) * avgFrameTimeSecs + alpha * frameTimeSecs;
      frameRate=(float)(1.0 / avgFrameTimeSecs);
    }
    if (frameCount != 0) {
      handleMethods("pre");
    }
    pmouseX=dmouseX;
    pmouseY=dmouseY;
    draw();
    dmouseX=mouseX;
    dmouseY=mouseY;
    dequeueEvents();
    handleMethods("draw");
    redraw=false;
  }
  g.endDraw();
  if (recorder != null) {
    recorder.endDraw();
  }
  insideDraw=false;
  if (frameCount != 0) {
    handleMethods("post");
  }
  frameRateLastNanos=now;
  frameCount++;
}

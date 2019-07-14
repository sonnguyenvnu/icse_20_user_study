@Override public void hint(int which){
  boolean oldValue=hints[PApplet.abs(which)];
  super.hint(which);
  boolean newValue=hints[PApplet.abs(which)];
  if (oldValue == newValue) {
    return;
  }
  if (which == DISABLE_DEPTH_TEST) {
    flush();
    pgl.disable(PGL.DEPTH_TEST);
  }
 else   if (which == ENABLE_DEPTH_TEST) {
    flush();
    pgl.enable(PGL.DEPTH_TEST);
  }
 else   if (which == DISABLE_DEPTH_MASK) {
    flush();
    pgl.depthMask(false);
  }
 else   if (which == ENABLE_DEPTH_MASK) {
    flush();
    pgl.depthMask(true);
  }
 else   if (which == ENABLE_OPTIMIZED_STROKE) {
    flush();
    setFlushMode(FLUSH_WHEN_FULL);
  }
 else   if (which == DISABLE_OPTIMIZED_STROKE) {
    if (is2D()) {
      PGraphics.showWarning("Optimized strokes can only be disabled in 3D");
    }
 else {
      flush();
      setFlushMode(FLUSH_CONTINUOUSLY);
    }
  }
 else   if (which == DISABLE_STROKE_PERSPECTIVE) {
    if (0 < tessGeo.lineVertexCount && 0 < tessGeo.lineIndexCount) {
      flush();
    }
  }
 else   if (which == ENABLE_STROKE_PERSPECTIVE) {
    if (0 < tessGeo.lineVertexCount && 0 < tessGeo.lineIndexCount) {
      flush();
    }
  }
 else   if (which == ENABLE_DEPTH_SORT) {
    if (is3D()) {
      flush();
      if (sorter == null)       sorter=new DepthSorter(this);
      isDepthSortingEnabled=true;
    }
 else {
      PGraphics.showWarning("Depth sorting can only be enabled in 3D");
    }
  }
 else   if (which == DISABLE_DEPTH_SORT) {
    if (is3D()) {
      flush();
      isDepthSortingEnabled=false;
    }
  }
 else   if (which == ENABLE_BUFFER_READING) {
    restartPGL();
  }
 else   if (which == DISABLE_BUFFER_READING) {
    restartPGL();
  }
}

/** 
 * ( begin auto-generated from hint.xml ) Set various hints and hacks for the renderer. This is used to handle obscure rendering features that cannot be implemented in a consistent manner across renderers. Many options will often graduate to standard features instead of hints over time. <br/> <br/> hint(ENABLE_OPENGL_4X_SMOOTH) - Enable 4x anti-aliasing for P3D. This can help force anti-aliasing if it has not been enabled by the user. On some graphics cards, this can also be set by the graphics driver's control panel, however not all cards make this available. This hint must be called immediately after the size() command because it resets the renderer, obliterating any settings and anything drawn (and like size(), re-running the code that came before it again). <br/> <br/> hint(DISABLE_OPENGL_2X_SMOOTH) - In Processing 1.0, Processing always enables 2x smoothing when the P3D renderer is used. This hint disables the default 2x smoothing and returns the smoothing behavior found in earlier releases, where smooth() and noSmooth() could be used to enable and disable smoothing, though the quality was inferior. <br/> <br/> hint(ENABLE_NATIVE_FONTS) - Use the native version fonts when they are installed, rather than the bitmapped version from a .vlw file. This is useful with the default (or JAVA2D) renderer setting, as it will improve font rendering speed. This is not enabled by default, because it can be misleading while testing because the type will look great on your machine (because you have the font installed) but lousy on others' machines if the identical font is unavailable. This option can only be set per-sketch, and must be called before any use of textFont(). <br/> <br/> hint(DISABLE_DEPTH_TEST) - Disable the zbuffer, allowing you to draw on top of everything at will. When depth testing is disabled, items will be drawn to the screen sequentially, like a painting. This hint is most often used to draw in 3D, then draw in 2D on top of it (for instance, to draw GUI controls in 2D on top of a 3D interface). Starting in release 0149, this will also clear the depth buffer. Restore the default with hint(ENABLE_DEPTH_TEST), but note that with the depth buffer cleared, any 3D drawing that happens later in draw() will ignore existing shapes on the screen. <br/> <br/> hint(ENABLE_DEPTH_SORT) - Enable primitive z-sorting of triangles and lines in P3D and OPENGL. This can slow performance considerably, and the algorithm is not yet perfect. Restore the default with hint(DISABLE_DEPTH_SORT). <br/> <br/> hint(DISABLE_OPENGL_ERROR_REPORT) - Speeds up the P3D renderer setting by not checking for errors while running. Undo with hint(ENABLE_OPENGL_ERROR_REPORT). <br/> <br/> hint(ENABLE_BUFFER_READING) - Depth and stencil buffers in P2D/P3D will be downsampled to make PGL#readPixels work with multisampling. Enabling this introduces some overhead, so if you experience bad performance, disable multisampling with noSmooth() instead. This hint is not intended to be enabled and disabled repeatedely, so call this once in setup() or after creating your PGraphics2D/3D. You can restore the default with hint(DISABLE_BUFFER_READING) if you don't plan to read depth from this PGraphics anymore. <br/> <br/> hint(ENABLE_KEY_REPEAT) - Auto-repeating key events are discarded by default (works only in P2D/P3D); use this hint to get all the key events (including auto-repeated). Call hint(DISABLE_KEY_REPEAT) to get events only when the key goes physically up or down. <br/> <br/> hint(DISABLE_ASYNC_SAVEFRAME) - P2D/P3D only - save() and saveFrame() will not use separate threads for saving and will block until the image is written to the drive. This was the default behavior in 3.0b7 and before. To enable, call hint(ENABLE_ASYNC_SAVEFRAME). <br/> <br/> As of release 0149, unhint() has been removed in favor of adding additional ENABLE/DISABLE constants to reset the default behavior. This prevents the double negatives, and also reinforces which hints can be enabled or disabled. ( end auto-generated )
 * @webref rendering
 * @param which name of the hint to be enabled or disabled
 * @see PGraphics
 * @see PApplet#createGraphics(int,int,String,String)
 * @see PApplet#size(int,int)
 */
@SuppressWarnings("deprecation") public void hint(int which){
  if (which == ENABLE_NATIVE_FONTS || which == DISABLE_NATIVE_FONTS) {
    showWarning("hint(ENABLE_NATIVE_FONTS) no longer supported. " + "Use createFont() instead.");
  }
  if (which == ENABLE_KEY_REPEAT) {
    parent.keyRepeatEnabled=true;
  }
 else   if (which == DISABLE_KEY_REPEAT) {
    parent.keyRepeatEnabled=false;
  }
  if (which > 0) {
    hints[which]=true;
  }
 else {
    hints[-which]=false;
  }
}

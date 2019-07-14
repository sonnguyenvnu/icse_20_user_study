/** 
 * Initializes required EGL parameters and creates the  {@link SurfaceTexture}.
 * @param secureMode The {@link SecureMode} to be used for EGL surface.
 */
public void init(@SecureMode int secureMode){
  display=getDefaultDisplay();
  EGLConfig config=chooseEGLConfig(display);
  context=createEGLContext(display,config,secureMode);
  surface=createEGLSurface(display,config,context,secureMode);
  generateTextureIds(textureIdHolder);
  texture=new SurfaceTexture(textureIdHolder[0]);
  texture.setOnFrameAvailableListener(this);
}

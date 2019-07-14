/** 
 * Builds camera motion renderers for use by the player.
 * @param context The {@link Context} associated with the player.
 * @param extensionRendererMode The extension renderer mode.
 * @param out An array to which the built renderers should be appended.
 */
protected void buildCameraMotionRenderers(Context context,@ExtensionRendererMode int extensionRendererMode,ArrayList<Renderer> out){
  out.add(new CameraMotionRenderer());
}

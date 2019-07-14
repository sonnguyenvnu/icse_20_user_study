/** 
 * Builds text renderers for use by the player.
 * @param context The {@link Context} associated with the player.
 * @param output An output for the renderers.
 * @param outputLooper The looper associated with the thread on which the output should be called.
 * @param extensionRendererMode The extension renderer mode.
 * @param out An array to which the built renderers should be appended.
 */
protected void buildTextRenderers(Context context,TextOutput output,Looper outputLooper,@ExtensionRendererMode int extensionRendererMode,ArrayList<Renderer> out){
  out.add(new TextRenderer(output,outputLooper));
}

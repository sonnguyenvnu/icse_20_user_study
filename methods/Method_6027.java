/** 
 * Extract renderer capabilities for the renderers created by the provided renderers factory.
 * @param renderersFactory A {@link RenderersFactory}.
 * @param drmSessionManager An optional {@link DrmSessionManager} used by the renderers.
 * @return The {@link RendererCapabilities} for each renderer created by the {@code renderersFactory}.
 */
public static RendererCapabilities[] getRendererCapabilities(RenderersFactory renderersFactory,@Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager){
  Renderer[] renderers=renderersFactory.createRenderers(new Handler(),new VideoRendererEventListener(){
  }
,new AudioRendererEventListener(){
  }
,(cues) -> {
  }
,(metadata) -> {
  }
,drmSessionManager);
  RendererCapabilities[] capabilities=new RendererCapabilities[renderers.length];
  for (int i=0; i < renderers.length; i++) {
    capabilities[i]=renderers[i].getCapabilities();
  }
  return capabilities;
}

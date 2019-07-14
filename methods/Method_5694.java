/** 
 * Determines whether tunneling should be enabled, replacing  {@link RendererConfiguration}s in {@code rendererConfigurations} with configurations that enable tunneling on the appropriaterenderers if so.
 * @param mappedTrackInfo Mapped track information.
 * @param rendererConfigurations The renderer configurations. Configurations may be replaced withones that enable tunneling as a result of this call.
 * @param trackSelections The renderer track selections.
 * @param tunnelingAudioSessionId The audio session id to use when tunneling, or {@link C#AUDIO_SESSION_ID_UNSET} if tunneling should not be enabled.
 */
private static void maybeConfigureRenderersForTunneling(MappedTrackInfo mappedTrackInfo,int[][][] renderererFormatSupports,@NullableType RendererConfiguration[] rendererConfigurations,@NullableType TrackSelection[] trackSelections,int tunnelingAudioSessionId){
  if (tunnelingAudioSessionId == C.AUDIO_SESSION_ID_UNSET) {
    return;
  }
  int tunnelingAudioRendererIndex=-1;
  int tunnelingVideoRendererIndex=-1;
  boolean enableTunneling=true;
  for (int i=0; i < mappedTrackInfo.getRendererCount(); i++) {
    int rendererType=mappedTrackInfo.getRendererType(i);
    TrackSelection trackSelection=trackSelections[i];
    if ((rendererType == C.TRACK_TYPE_AUDIO || rendererType == C.TRACK_TYPE_VIDEO) && trackSelection != null) {
      if (rendererSupportsTunneling(renderererFormatSupports[i],mappedTrackInfo.getTrackGroups(i),trackSelection)) {
        if (rendererType == C.TRACK_TYPE_AUDIO) {
          if (tunnelingAudioRendererIndex != -1) {
            enableTunneling=false;
            break;
          }
 else {
            tunnelingAudioRendererIndex=i;
          }
        }
 else {
          if (tunnelingVideoRendererIndex != -1) {
            enableTunneling=false;
            break;
          }
 else {
            tunnelingVideoRendererIndex=i;
          }
        }
      }
    }
  }
  enableTunneling&=tunnelingAudioRendererIndex != -1 && tunnelingVideoRendererIndex != -1;
  if (enableTunneling) {
    RendererConfiguration tunnelingRendererConfiguration=new RendererConfiguration(tunnelingAudioSessionId);
    rendererConfigurations[tunnelingAudioRendererIndex]=tunnelingRendererConfiguration;
    rendererConfigurations[tunnelingVideoRendererIndex]=tunnelingRendererConfiguration;
  }
}

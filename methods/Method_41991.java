/** 
 * Internal methods 
 */
private void initializePlayer(){
  Intent intent=getIntent();
  boolean needNewPlayer=player == null;
  if (needNewPlayer) {
    TrackSelection.Factory adaptiveTrackSelectionFactory=new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
    trackSelector=new DefaultTrackSelector(adaptiveTrackSelectionFactory);
    trackSelectionHelper=new TrackSelectionHelper(trackSelector,adaptiveTrackSelectionFactory,getThemeHelper());
    lastSeenTrackGroupArray=null;
    UUID drmSchemeUuid=intent.hasExtra(DRM_SCHEME_UUID_EXTRA) ? UUID.fromString(intent.getStringExtra(DRM_SCHEME_UUID_EXTRA)) : null;
    DrmSessionManager<FrameworkMediaCrypto> drmSessionManager=null;
    if (drmSchemeUuid != null) {
      String drmLicenseUrl=intent.getStringExtra(DRM_LICENSE_URL);
      String[] keyRequestPropertiesArray=intent.getStringArrayExtra(DRM_KEY_REQUEST_PROPERTIES);
      boolean multiSession=intent.getBooleanExtra(DRM_MULTI_SESSION,false);
      int errorStringId=R.string.error_drm_unknown;
      try {
        drmSessionManager=buildDrmSessionManagerV18(drmSchemeUuid,drmLicenseUrl,keyRequestPropertiesArray,multiSession);
      }
 catch (      UnsupportedDrmException e) {
        errorStringId=e.reason == UnsupportedDrmException.REASON_UNSUPPORTED_SCHEME ? R.string.error_drm_unsupported_scheme : R.string.error_drm_unknown;
      }
      if (drmSessionManager == null) {
        showToast(errorStringId);
        return;
      }
    }
    DefaultRenderersFactory renderersFactory=new DefaultRenderersFactory(this,drmSessionManager,DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER);
    player=ExoPlayerFactory.newSimpleInstance(renderersFactory,trackSelector);
    player.addListener(new PlayerEventListener());
    simpleExoPlayerView.setPlayer(player);
    player.setPlayWhenReady(shouldAutoPlay);
  }
  String action=intent.getAction();
  Uri uris[];
  String extensions[];
  if (intent.getData() != null && intent.getType() != null) {
    uris=new Uri[]{intent.getData()};
    extensions=new String[]{intent.getType()};
  }
 else {
    showToast(getString(R.string.unexpected_intent_action,action));
    return;
  }
  MediaSource[] mediaSources=new MediaSource[uris.length];
  for (int i=0; i < uris.length; i++) {
    mediaSources[i]=buildMediaSource(uris[i],extensions[i]);
  }
  MediaSource mediaSource=mediaSources.length == 1 ? mediaSources[0] : new ConcatenatingMediaSource(mediaSources);
  boolean haveResumePosition=resumeWindow != C.INDEX_UNSET;
  if (haveResumePosition) {
    player.seekTo(resumeWindow,resumePosition);
  }
  player.prepare(mediaSource,!haveResumePosition,false);
  inErrorState=false;
  supportInvalidateOptionsMenu();
}

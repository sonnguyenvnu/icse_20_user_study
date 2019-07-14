private BackendExampleEntry createLocalDrawableExample(){
  return new BackendExampleEntry(){
    @Override public AnimationBackend createBackend(){
      return new LocalDrawableAnimationBackend.Builder(mContext.getResources()).addDrawableFrame(R.mipmap.ic_alarm).addDrawableFrame(R.mipmap.ic_android).addDrawableFrame(R.mipmap.ic_launcher).loopCount(3).frameDurationMs(500).build();
    }
    @Override public int getTitleResId(){
      return R.string.backend_local_drawables;
    }
  }
;
}

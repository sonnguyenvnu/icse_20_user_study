private void clearRenderedFirstFrame(){
  renderedFirstFrame=false;
  if (Util.SDK_INT >= 23 && tunneling) {
    MediaCodec codec=getCodec();
    if (codec != null) {
      tunnelingOnFrameRenderedListener=new OnFrameRenderedListenerV23(codec);
    }
  }
}

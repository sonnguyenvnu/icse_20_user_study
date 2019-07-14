@Override protected void onInputFormatChanged(Format newFormat) throws ExoPlaybackException {
  super.onInputFormatChanged(newFormat);
  eventDispatcher.inputFormatChanged(newFormat);
  pendingPixelWidthHeightRatio=newFormat.pixelWidthHeightRatio;
  pendingRotationDegrees=newFormat.rotationDegrees;
}

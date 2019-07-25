@Override public void setup(){
  mExtractor.selectTrack(mTrackIndex);
  try {
    mEncoder=MediaCodec.createEncoderByType(mOutputFormat.getString(MediaFormat.KEY_MIME));
  }
 catch (  IOException e) {
    throw new IllegalStateException(e);
  }
  mEncoder.configure(mOutputFormat,null,null,MediaCodec.CONFIGURE_FLAG_ENCODE);
  mEncoderInputSurfaceWrapper=new InputSurface(mEncoder.createInputSurface());
  mEncoderInputSurfaceWrapper.makeCurrent();
  mEncoder.start();
  mEncoderStarted=true;
  mEncoderOutputBuffers=mEncoder.getOutputBuffers();
  MediaFormat inputFormat=mExtractor.getTrackFormat(mTrackIndex);
  if (inputFormat.containsKey(MediaFormatExtraConstants.KEY_ROTATION_DEGREES)) {
    inputFormat.setInteger(MediaFormatExtraConstants.KEY_ROTATION_DEGREES,0);
  }
  mDecoderOutputSurfaceWrapper=new OutputSurface();
  try {
    mDecoder=MediaCodec.createDecoderByType(inputFormat.getString(MediaFormat.KEY_MIME));
  }
 catch (  IOException e) {
    throw new IllegalStateException(e);
  }
  mDecoder.configure(inputFormat,mDecoderOutputSurfaceWrapper.getSurface(),null,0);
  mDecoder.start();
  mDecoderStarted=true;
  mDecoderInputBuffers=mDecoder.getInputBuffers();
}

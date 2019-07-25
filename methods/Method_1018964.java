@Override public final void display(MediaPlayer mediaPlayer,ByteBuffer[] nativeBuffers,BufferFormat bufferFormat){
  nativeBuffers[0].asIntBuffer().get(buffer,0,bufferFormat.getHeight() * bufferFormat.getWidth());
  onDisplay(mediaPlayer,buffer);
}

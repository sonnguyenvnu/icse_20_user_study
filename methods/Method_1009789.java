@Override public void rendering(byte[] pixel){
synchronized (syncRenderThread) {
    glesRenderThread.updatePixel(pixel);
  }
}

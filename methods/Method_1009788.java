@Override public void update(int visualWidth,int visualHeight){
synchronized (syncRenderThread) {
    glesRenderThread.updateVisualWH(visualWidth,visualHeight);
  }
}

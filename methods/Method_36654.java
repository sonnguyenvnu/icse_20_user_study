@Override public void init(TangramEngine tangramEngine){
  this.tangramEngine=tangramEngine;
  vafContext=new VafContext(tangramEngine.getContext());
  tangramEngine.register(VafContext.class,vafContext);
  vafContext.getViewManager().init(tangramEngine.getContext());
  vafContext.setImageLoaderAdapter(imageLoaderAdapter);
  vafContext.getViewManager().loadBinBufferSync(VVTEST.BIN);
  vafContext.getViewManager().loadBinBufferSync(DEBUG.BIN);
}

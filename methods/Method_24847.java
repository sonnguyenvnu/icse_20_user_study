private void fireListeners(PreprocessedSketch ps){
  for (  Consumer<PreprocessedSketch> listener : listeners) {
    try {
      listener.accept(ps);
    }
 catch (    Exception e) {
      Messages.loge("error when firing preprocessing listener",e);
    }
  }
}

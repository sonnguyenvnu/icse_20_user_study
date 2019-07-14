private BackendExampleEntry createColorExample(){
  return new BackendExampleEntry(){
    @Override public AnimationBackend createBackend(){
      return ExampleColorBackend.createSampleColorAnimationBackend(mSpinner.getContext().getResources());
    }
    @Override public int getTitleResId(){
      return R.string.backend_color;
    }
  }
;
}

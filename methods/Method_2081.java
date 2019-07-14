@Override public void onViewCreated(View view,@Nullable Bundle savedInstanceState){
  if (CustomImageFormatConfigurator.isKeyframesEnabled()) {
    initAnimation(view);
  }
}

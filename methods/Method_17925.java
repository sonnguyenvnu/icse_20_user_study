protected void onMeasure(ComponentContext c,ComponentLayout layout,int widthSpec,int heightSpec,Size size){
  throw new IllegalStateException("You must override onMeasure() if you return true in canMeasure(), " + "ComponentLifecycle is: " + this);
}

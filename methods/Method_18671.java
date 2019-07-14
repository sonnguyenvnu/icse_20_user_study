@OnMeasure protected static void onMeasure(ComponentContext c,ComponentLayout layout,int widthSpec,int heightSpec,Size size,@Prop(optional=true,resType=ResType.FLOAT) float imageAspectRatio){
  MeasureUtils.measureWithAspectRatio(widthSpec,heightSpec,imageAspectRatio,size);
}

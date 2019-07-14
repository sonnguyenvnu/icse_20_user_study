@OnMeasure static void onMeasure(ComponentContext context,ComponentLayout layout,int widthSpec,int heightSpec,Size size,@Prop(optional=true,resType=ResType.FLOAT) float imageAspectRatio){
  MeasureUtils.measureWithAspectRatio(widthSpec,heightSpec,imageAspectRatio,size);
}

@Override protected void onMeasure(ComponentContext c,ComponentLayout layout,int widthSpec,int heightSpec,Size size){
  final V toMeasure=(V)ComponentsPools.acquireMountContent(c.getAndroidContext(),this);
  final ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(size.width,size.height);
  toMeasure.setLayoutParams(layoutParams);
  mViewBinder.bind(toMeasure);
  if (toMeasure.getVisibility() == View.GONE) {
    size.width=0;
    size.height=0;
  }
 else {
    toMeasure.measure(widthSpec,heightSpec);
    size.width=toMeasure.getMeasuredWidth();
    size.height=toMeasure.getMeasuredHeight();
  }
  mViewBinder.unbind(toMeasure);
  ComponentsPools.release(c.getAndroidContext(),this,toMeasure);
}

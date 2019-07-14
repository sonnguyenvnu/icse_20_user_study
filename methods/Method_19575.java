@OnMeasure static void onMeasure(ComponentContext c,ComponentLayout layout,int widthSpec,int heightSpec,Size size){
  final ClockView clockView=new ClockView(c.getAndroidContext());
  clockView.measure(MeasureUtils.getViewMeasureSpec(widthSpec),MeasureUtils.getViewMeasureSpec(heightSpec));
  size.width=clockView.getMeasuredWidth();
  size.height=clockView.getMeasuredHeight();
}

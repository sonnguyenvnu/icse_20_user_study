@OnBind static void onBind(ComponentContext c,MatrixDrawable mountedDrawable,@FromBoundsDefined Integer drawableWidth,@FromBoundsDefined Integer drawableHeight){
  mountedDrawable.bind(drawableWidth,drawableHeight);
}

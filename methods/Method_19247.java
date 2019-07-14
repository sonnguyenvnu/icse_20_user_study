@OnMount static void onMount(ComponentContext c,MatrixDrawable matrixDrawable,@Prop(resType=ResType.DRAWABLE) Drawable drawable,@FromBoundsDefined DrawableMatrix drawableMatrix){
  matrixDrawable.mount(drawable,drawableMatrix);
}

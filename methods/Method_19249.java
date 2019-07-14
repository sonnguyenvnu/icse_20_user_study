@ShouldUpdate(onMount=true) static boolean shouldUpdate(@Prop(optional=true) Diff<ScaleType> scaleType,@Prop(resType=ResType.DRAWABLE) Diff<Drawable> drawable){
  return (scaleType.getNext() != scaleType.getPrevious()) || drawable.getNext() != drawable.getPrevious();
}

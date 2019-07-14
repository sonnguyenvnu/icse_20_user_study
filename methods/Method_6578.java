public void setParentView(View view){
  parentView=view;
  AnimatedFileDrawable animation=getAnimation();
  if (animation != null) {
    animation.setParentView(parentView);
  }
}

public Animation getListAnimation(boolean expanded){
  Timeline animation=new Timeline();
  createAnimation(expanded,animation);
  return animation;
}

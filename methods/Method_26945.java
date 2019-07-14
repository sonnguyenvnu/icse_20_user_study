public void moveView(@NonNull View view,int left,int top){
  if (view.getParent() == this) {
    view.setLayoutParams(initParams(view,left,top));
  }
}

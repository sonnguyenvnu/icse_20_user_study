public void onDrag(float dx,float dy){
  if (animating) {
    return;
  }
  state.translate(dx,dy);
  updateMatrix();
}

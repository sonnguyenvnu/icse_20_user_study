final boolean shouldComponentUpdate(Component previous,Component next){
  if (isPureRender()) {
    return shouldUpdate(previous,next);
  }
  return true;
}

private BiFunction<Boolean,Duration,Collection<KeyFrame>> initDefaultAnimation(Node child){
  return (expanded,duration) -> {
    ArrayList<KeyFrame> frames=new ArrayList<>();
    frames.add(new KeyFrame(duration,event -> {
      child.setScaleX(expanded ? 1 : 0);
      child.setScaleY(expanded ? 1 : 0);
    }
,new KeyValue(child.scaleXProperty(),expanded ? 1 : 0,Interpolator.EASE_BOTH),new KeyValue(child.scaleYProperty(),expanded ? 1 : 0,Interpolator.EASE_BOTH)));
    return frames;
  }
;
}

private KeyFrame[] getKeyFrames(double angle,double duration,Paint color){
  KeyFrame[] frames=new KeyFrame[4];
  frames[0]=new KeyFrame(Duration.seconds(duration),new KeyValue(arc.lengthProperty(),5,Interpolator.LINEAR),new KeyValue(arc.startAngleProperty(),angle + 45 + control.getStartingAngle(),Interpolator.LINEAR));
  frames[1]=new KeyFrame(Duration.seconds(duration + 0.4),new KeyValue(arc.lengthProperty(),250,Interpolator.LINEAR),new KeyValue(arc.startAngleProperty(),angle + 90 + control.getStartingAngle(),Interpolator.LINEAR));
  frames[2]=new KeyFrame(Duration.seconds(duration + 0.7),new KeyValue(arc.lengthProperty(),250,Interpolator.LINEAR),new KeyValue(arc.startAngleProperty(),angle + 135 + control.getStartingAngle(),Interpolator.LINEAR));
  frames[3]=new KeyFrame(Duration.seconds(duration + 1.1),new KeyValue(arc.lengthProperty(),5,Interpolator.LINEAR),new KeyValue(arc.startAngleProperty(),angle + 435 + control.getStartingAngle(),Interpolator.LINEAR),new KeyValue(arc.strokeProperty(),color,Interpolator.EASE_BOTH));
  return frames;
}

private void createAnimation(boolean expanded,Timeline animation){
  final ObservableList<Node> children=getChildren();
  double duration=160 / (double)children.size();
  if (expanded) {
    for (    Node child : children) {
      child.setVisible(true);
    }
  }
  for (int i=1; i < children.size(); i++) {
    Node child=children.get(i);
    Collection<KeyFrame> frames=animationsMap.get(child).apply(expanded,Duration.millis(i * duration));
    animation.getKeyFrames().addAll(frames);
  }
  Collection<KeyFrame> frames=animationsMap.get(children.get(0)).apply(expanded,Duration.millis(160));
  animation.getKeyFrames().addAll(frames);
  if (!expanded) {
    animation.setOnFinished((finish) -> {
      for (int i=1; i < children.size(); i++) {
        children.get(i).setVisible(false);
      }
    }
);
  }
 else {
    animation.setOnFinished(null);
  }
}

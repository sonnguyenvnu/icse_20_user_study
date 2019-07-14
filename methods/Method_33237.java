private void playIndeterminateAnimation(Boolean indeterminate,boolean playAnimation){
  if (indeterminate == null) {
    indeterminate=false;
  }
  indeterminateTransition.setRate(indeterminate ? 1 : -1);
  if (playAnimation) {
    indeterminateTransition.play();
  }
 else {
    if (indeterminate) {
      CornerRadii radii=indeterminateMark.getBackground() == null ? null : indeterminateMark.getBackground().getFills().get(0).getRadii();
      Insets insets=indeterminateMark.getBackground() == null ? null : indeterminateMark.getBackground().getFills().get(0).getInsets();
      indeterminateMark.setOpacity(1);
      indeterminateMark.setScaleY(1);
      indeterminateMark.setScaleX(1);
      indeterminateMark.setBackground(new Background(new BackgroundFill(getSkinnable().getCheckedColor(),radii,insets)));
      indeterminateTransition.playFrom(indeterminateTransition.getCycleDuration());
    }
 else {
      indeterminateMark.setOpacity(0);
      indeterminateMark.setScaleY(0);
      indeterminateMark.setScaleX(0);
      indeterminateTransition.playFrom(Duration.ZERO);
    }
  }
  if (getSkinnable().isSelected()) {
    playSelectAnimation(!indeterminate,playAnimation);
  }
}

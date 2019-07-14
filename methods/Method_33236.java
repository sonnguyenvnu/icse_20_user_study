private void playSelectAnimation(Boolean selection,boolean playAnimation){
  if (selection == null) {
    selection=false;
  }
  transition.setRate(selection ? 1 : -1);
  select.setRate(selection ? 1 : -1);
  if (playAnimation) {
    transition.play();
    select.play();
  }
 else {
    CornerRadii radii=box.getBackground() == null ? null : box.getBackground().getFills().get(0).getRadii();
    Insets insets=box.getBackground() == null ? null : box.getBackground().getFills().get(0).getInsets();
    if (selection) {
      mark.setScaleY(1);
      mark.setScaleX(1);
      mark.setOpacity(1);
      box.setBackground(new Background(new BackgroundFill(getSkinnable().getCheckedColor(),radii,insets)));
      select.playFrom(select.getCycleDuration());
      transition.playFrom(transition.getCycleDuration());
    }
 else {
      mark.setScaleY(0);
      mark.setScaleX(0);
      mark.setOpacity(0);
      box.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,radii,insets)));
      select.playFrom(Duration.ZERO);
      transition.playFrom(Duration.ZERO);
    }
  }
  box.setBorder(new Border(new BorderStroke(selection ? getSkinnable().getCheckedColor() : getSkinnable().getUnCheckedColor(),BorderStrokeStyle.SOLID,new CornerRadii(2),new BorderWidths(2))));
}

private void updateColors(){
  final Paint color=getSkinnable().isSelected() ? getSkinnable().getCheckedColor() : getSkinnable().getUnCheckedColor();
  JFXNodeUtils.updateBackground(indeterminateMark.getBackground(),indeterminateMark,getSkinnable().getCheckedColor());
  JFXNodeUtils.updateBackground(box.getBackground(),box,getSkinnable().isSelected() ? getSkinnable().getCheckedColor() : Color.TRANSPARENT);
  rippler.setRipplerFill(color);
  final BorderStroke borderStroke=box.getBorder().getStrokes().get(0);
  box.setBorder(new Border(new BorderStroke(color,borderStroke.getTopStyle(),borderStroke.getRadii(),borderStroke.getWidths())));
}

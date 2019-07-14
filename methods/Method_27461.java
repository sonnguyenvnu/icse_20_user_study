@Override public void bind(@NonNull LabelModel labelModel){
  name.setText(labelModel.getName());
  if (labelModel.getColor() != null) {
    int color=Color.parseColor(labelModel.getColor().startsWith("#") ? labelModel.getColor() : "#" + labelModel.getColor());
    colorImage.setBackgroundColor(color);
    if (onSelectLabel != null) {
      if (onSelectLabel.isLabelSelected(labelModel)) {
        name.setTextColor(ViewHelper.generateTextColor(color));
      }
 else {
        name.setTextColor(ViewHelper.getPrimaryTextColor(itemView.getContext()));
      }
      itemView.setBackgroundColor(onSelectLabel.isLabelSelected(labelModel) ? color : 0);
    }
  }
 else {
    colorImage.setBackgroundColor(0);
  }
}

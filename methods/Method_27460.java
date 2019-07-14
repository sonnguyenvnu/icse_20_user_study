@Override public void bind(@NonNull String labelModel){
  int labelColor=Color.parseColor(labelModel);
  itemView.setBackgroundColor(labelColor);
  color.setTextColor(ViewHelper.generateTextColor(labelColor));
  color.setText(labelModel);
}

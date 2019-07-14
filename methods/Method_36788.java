protected void renderBackground(BaseCell cell,View view){
  if (cell.style != null) {
    if (cell.style.bgColor != 0) {
      view.setBackgroundColor(cell.style.bgColor);
    }
  }
}

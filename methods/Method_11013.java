private void positiveButtonOnClick(DialogInterface dialog,ColorPickerClickListener onClickListener){
  int selectedColor=colorPickerView.getSelectedColor();
  Integer[] allColors=colorPickerView.getAllColors();
  onClickListener.onClick(dialog,selectedColor,allColors);
}

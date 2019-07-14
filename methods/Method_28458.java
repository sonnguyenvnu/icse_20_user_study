@Override protected void onClick(){
  super.onClick();
  int selected_color=getSelectedColor();
  String title=String.format("Accent Color: (Currently: %s)",getSelectedColorName());
  ColorPicker colorPicker=new ColorPicker(getContext());
  colorPicker.setRoundColorButton(true);
  colorPicker.setColors(R.array.theme_colors_hex);
  colorPicker.setDefaultColorButton(selected_color);
  colorPicker.setTitle(title);
  TextView title_tv=colorPicker.getDialogViewLayout().findViewById(R.id.title);
  title_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
  colorPicker.getPositiveButton().setTextColor(ViewHelper.getPrimaryTextColor(getContext()));
  colorPicker.getNegativeButton().setTextColor(ViewHelper.getPrimaryTextColor(getContext()));
  colorPicker.setOnChooseColorListener(this);
  colorPicker.show();
}

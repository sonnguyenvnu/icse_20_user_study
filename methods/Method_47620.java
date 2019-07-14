public ColorPickerDialog create(int paletteColor){
  ColorPickerDialog dialog=new ColorPickerDialog();
  StyledResources res=new StyledResources(context);
  int color=PaletteUtils.getColor(context,paletteColor);
  dialog.initialize(R.string.color_picker_default_title,res.getPalette(),color,4,com.android.colorpicker.ColorPickerDialog.SIZE_SMALL);
  return dialog;
}

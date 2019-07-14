/** 
 * Creates a color swatch.
 */
private ColorPickerSwatch createColorSwatch(int color,int selectedColor){
  ColorPickerSwatch view=new ColorPickerSwatch(getContext(),color,color == selectedColor,mOnColorSelectedListener);
  TableRow.LayoutParams params=new TableRow.LayoutParams(mSwatchLength,mSwatchLength);
  params.setMargins(mMarginSize,mMarginSize,mMarginSize,mMarginSize);
  view.setLayoutParams(params);
  return view;
}

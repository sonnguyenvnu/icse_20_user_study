/** 
 * Creates a blank space to fill the row.
 */
private ImageView createBlankSpace(){
  ImageView view=new ImageView(getContext());
  TableRow.LayoutParams params=new TableRow.LayoutParams(mSwatchLength,mSwatchLength);
  params.setMargins(mMarginSize,mMarginSize,mMarginSize,mMarginSize);
  view.setLayoutParams(params);
  return view;
}

/** 
 * Initialize the size, columns, and listener.  Size should be a pre-defined size (SIZE_LARGE or SIZE_SMALL) from ColorPickerDialogFragment.
 */
public void init(int size,int columns,OnColorSelectedListener listener){
  mNumColumns=columns;
  Resources res=getResources();
  if (size == ColorPickerDialog.SIZE_LARGE) {
    mSwatchLength=res.getDimensionPixelSize(R.dimen.color_swatch_large);
    mMarginSize=res.getDimensionPixelSize(R.dimen.color_swatch_margins_large);
  }
 else {
    mSwatchLength=res.getDimensionPixelSize(R.dimen.color_swatch_small);
    mMarginSize=res.getDimensionPixelSize(R.dimen.color_swatch_margins_small);
  }
  mOnColorSelectedListener=listener;
  mDescription=res.getString(R.string.color_swatch_description);
  mDescriptionSelected=res.getString(R.string.color_swatch_description_selected);
}

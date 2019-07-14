/** 
 * @param drawable custom selection drawable
 */
public void setSelectionDrawable(Drawable drawable){
  if (drawable == null) {
    this.selectionDrawable=null;
  }
 else {
    this.selectionDrawable=drawable.getConstantState().newDrawable(getResources());
  }
  regenerateBackground();
}

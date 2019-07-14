/** 
 * Set layout margins for a ViewGroup with LinearLayout parent.
 */
public static void setLinearViewGroupMargins(final @NonNull ViewGroup viewGroup,final int leftMargin,final int topMargin,final int rightMargin,final int bottomMargin){
  final LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(viewGroup.getLayoutParams());
  layoutParams.setMargins(leftMargin,topMargin,rightMargin,bottomMargin);
  viewGroup.setLayoutParams(layoutParams);
}

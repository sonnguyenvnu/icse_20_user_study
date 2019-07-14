/** 
 * Set layout margins for a ViewGroup with RelativeLayout parent.
 */
public static void setRelativeViewGroupMargins(final @NonNull ViewGroup viewGroup,final int leftMargin,final int topMargin,final int rightMargin,final int bottomMargin){
  final RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(viewGroup.getLayoutParams());
  layoutParams.setMargins(leftMargin,topMargin,rightMargin,bottomMargin);
  viewGroup.setLayoutParams(layoutParams);
}

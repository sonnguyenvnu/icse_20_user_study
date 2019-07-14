/** 
 * Adjust spacing between cards when metadata label is present.
 */
private void adjustViewGroupTopMargin(final @NonNull ViewGroup viewGroup,final int topMargin){
  final RelativeLayout.MarginLayoutParams marginParams=new RelativeLayout.MarginLayoutParams(viewGroup.getLayoutParams());
  marginParams.setMargins(0,topMargin,0,0);
  viewGroup.setLayoutParams(marginParams);
}

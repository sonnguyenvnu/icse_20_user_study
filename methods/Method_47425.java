/** 
 * Method switches icon resources as per current theme
 */
private void switchIcons(){
  if (getAppTheme().equals(AppTheme.DARK) || getAppTheme().equals(AppTheme.BLACK)) {
    mAuthorsDivider.setBackgroundColor(Utils.getColor(this,R.color.divider_dark_card));
    mDeveloper1Divider.setBackgroundColor(Utils.getColor(this,R.color.divider_dark_card));
  }
}

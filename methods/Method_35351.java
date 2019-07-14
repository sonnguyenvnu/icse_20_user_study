/** 
 * An easy way to set up non-home(no back button on the toolbar) activity to enable go back action.
 * @param toolbar The toolbar with go back button
 * @return ActionBar
 */
protected ActionBar supportActionBar(Toolbar toolbar){
  setSupportActionBar(toolbar);
  ActionBar actionBar=getSupportActionBar();
  if (actionBar != null) {
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowHomeEnabled(true);
  }
  return actionBar;
}

/** 
 * Populate data to the presenter 
 */
public void populate(){
  for (int i=0; i < ITLCModelLaunchDataPresenter.ALL_FIELDS.length; i++) {
    informPresenter(ITLCModelLaunchDataPresenter.ALL_FIELDS[i]);
  }
}

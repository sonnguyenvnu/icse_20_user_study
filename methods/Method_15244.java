/** 
 * ?????mFooterView
 * @warn 1.????setAdapterNoFooter??; 2.???setAdapter???
 * @param show
 */
public void showFooter(boolean show){
  if (isFooterAdded) {
    if (show) {
      mFooterView.show();
    }
 else {
      mFooterView.hide();
    }
  }
}

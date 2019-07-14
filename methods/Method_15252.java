/** 
 * show footer
 */
public void show(){
  if (needFooter) {
    isShowing=true;
    LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams)mContentView.getLayoutParams();
    lp.height=android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
    mContentView.setLayoutParams(lp);
  }
}

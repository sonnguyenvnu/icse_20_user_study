/** 
 * ? {@link AudioRecorderPanel}???button??
 * @param rootView ???????rootView
 * @param button   ?????????
 */
public void attach(View rootView,Button button){
  this.rootView=rootView;
  this.button=button;
  this.button.setOnTouchListener(this);
}

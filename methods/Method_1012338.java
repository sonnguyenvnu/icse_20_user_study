/** 
 * Gloading(loading status view) wrap the specific view.
 * @param view view to be wrapped
 * @return Holder
 */
public Holder wrap(View view){
  FrameLayout wrapper=new FrameLayout(view.getContext());
  ViewGroup.LayoutParams lp=view.getLayoutParams();
  if (lp != null) {
    wrapper.setLayoutParams(lp);
  }
  if (view.getParent() != null) {
    ViewGroup parent=(ViewGroup)view.getParent();
    int index=parent.indexOfChild(view);
    parent.removeView(view);
    parent.addView(wrapper,index);
  }
  LayoutParams newLp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
  wrapper.addView(view,newLp);
  return new Holder(mAdapter,view.getContext(),wrapper);
}

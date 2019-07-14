private void listenForDeepestChildAttach(final View view,final ChildAttachListener attachListener){
  if (!(view instanceof ViewGroup)) {
    attachListener.onAttached();
    return;
  }
  ViewGroup viewGroup=(ViewGroup)view;
  if (viewGroup.getChildCount() == 0) {
    attachListener.onAttached();
    return;
  }
  childOnAttachStateChangeListener=new OnAttachStateChangeListener(){
    @Override public void onViewAttachedToWindow(    View v){
      if (!attached) {
        attached=true;
        attachListener.onAttached();
        v.removeOnAttachStateChangeListener(this);
        childOnAttachStateChangeListener=null;
      }
    }
    @Override public void onViewDetachedFromWindow(    View v){
    }
  }
;
  findDeepestChild(viewGroup).addOnAttachStateChangeListener(childOnAttachStateChangeListener);
}

public void setPressedHandleColor(int i){
  handle.setColorFilter(i);
  StateListDrawable stateListDrawable=new StateListDrawable();
  Drawable drawable=ContextCompat.getDrawable(getContext(),R.drawable.fastscroller_handle_normal);
  Drawable drawable1=ContextCompat.getDrawable(getContext(),R.drawable.fastscroller_handle_pressed);
  stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET,new InsetDrawable(drawable1,getResources().getDimensionPixelSize(R.dimen.fastscroller_track_padding),0,0,0));
  stateListDrawable.addState(View.EMPTY_STATE_SET,new InsetDrawable(drawable,getResources().getDimensionPixelSize(R.dimen.fastscroller_track_padding),0,0,0));
  this.handle.setImageDrawable(stateListDrawable);
}

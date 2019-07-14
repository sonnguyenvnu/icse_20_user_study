@Override public void onViewAttachedToWindow(@NonNull View v){
  v.removeOnAttachStateChangeListener(this);
  if (changeListener != null) {
    changeListener.onChangeCompleted();
    changeListener=null;
    container=null;
  }
}

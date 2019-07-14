@Override public void completeImmediately(){
  if (changeListener != null) {
    changeListener.onChangeCompleted();
    changeListener=null;
    container.removeOnAttachStateChangeListener(this);
    container=null;
  }
}

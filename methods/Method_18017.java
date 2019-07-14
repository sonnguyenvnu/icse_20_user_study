void notifyNodesHaveFinished(){
  if (mListener != null) {
    mListener.onAllNodesFinished(this);
  }
  deactivate();
}

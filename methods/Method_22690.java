@Override public void statusError(Exception exception){
  EventQueue.invokeLater(() -> wrapped.statusError(exception));
}

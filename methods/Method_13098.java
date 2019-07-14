public void updateHistoryActions(){
  invokeLater(() -> {
    backwardAction.setEnabled(history.canBackward());
    forwardAction.setEnabled(history.canForward());
  }
);
}

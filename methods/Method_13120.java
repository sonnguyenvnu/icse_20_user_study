public void updateProgressBar(int value){
  SwingUtil.invokeLater(() -> {
    saveAllSourcesProgressBar.setValue(value);
  }
);
}

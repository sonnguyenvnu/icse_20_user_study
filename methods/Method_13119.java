public void setMaxValue(int maxValue){
  SwingUtil.invokeLater(() -> {
    if (maxValue > 0) {
      saveAllSourcesProgressBar.setMaximum(maxValue);
      saveAllSourcesProgressBar.setIndeterminate(false);
    }
 else {
      saveAllSourcesProgressBar.setIndeterminate(true);
    }
  }
);
}

public void show(File file){
  SwingUtil.invokeLater(() -> {
    saveAllSourcesLabel.setText("Saving '" + file.getAbsolutePath() + "'...");
    saveAllSourcesProgressBar.setValue(0);
    saveAllSourcesProgressBar.setMaximum(10);
    saveAllSourcesProgressBar.setIndeterminate(true);
    saveAllSourcesDialog.pack();
    saveAllSourcesDialog.setLocationRelativeTo(saveAllSourcesDialog.getParent());
    saveAllSourcesDialog.setVisible(true);
  }
);
}

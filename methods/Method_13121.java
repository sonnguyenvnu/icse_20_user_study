public void hide(){
  SwingUtil.invokeLater(() -> {
    saveAllSourcesDialog.setVisible(false);
  }
);
}

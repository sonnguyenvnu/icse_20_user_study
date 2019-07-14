public void showActionFailedDialog(){
  SwingUtil.invokeLater(() -> {
    JOptionPane.showMessageDialog(saveAllSourcesDialog,"'Save All Sources' action failed.","Error",JOptionPane.ERROR_MESSAGE);
  }
);
}

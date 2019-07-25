private void reload(JComponent c){
  LOGGER.debug("Reloading...");
  ((Window)c.getTopLevelAncestor()).dispose();
  JOptionPane.showOptionDialog(SwingUtilities.getWindowAncestor((Component)PMS.get().getFrame()),config(),Messages.getString("Dialog.Options"),JOptionPane.CLOSED_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);
}

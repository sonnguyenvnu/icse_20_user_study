private void blurContributionPanel(Component component){
  component.setFocusable(false);
  component.setEnabled(false);
  if (component instanceof JComponent) {
    ((JComponent)component).setToolTipText(INCOMPATIBILITY_BLUR);
  }
  if (component instanceof Container) {
    for (    Component child : ((Container)component).getComponents()) {
      blurContributionPanel(child);
    }
  }
}

private void uninstall(Node node,Tooltip tooltip){
  if (tooltip instanceof JFXTooltip) {
    JFXTooltip.uninstall(node);
  }
 else {
    Tooltip.uninstall(node,tooltip);
  }
}

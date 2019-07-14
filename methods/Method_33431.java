private void install(Node node,Tooltip tooltip){
  if (tooltip == null) {
    return;
  }
  if (tooltip instanceof JFXTooltip) {
    JFXTooltip.install(node,(JFXTooltip)tooltip);
  }
 else {
    Tooltip.install(node,tooltip);
  }
}

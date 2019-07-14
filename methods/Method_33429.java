private void install(Node node,Tooltip oldVal,Tooltip newVal){
  if (oldVal != null && !oldVal.getStyleClass().contains(ERROR_TOOLTIP_STYLE_CLASS)) {
    node.getProperties().put(TEMP_TOOLTIP_KEY,oldVal);
  }
  if (node instanceof Control) {
    if (oldVal != null) {
      if (oldVal instanceof JFXTooltip) {
        JFXTooltip.uninstall(node);
      }
      if (newVal == null || !(newVal instanceof JFXTooltip)) {
        ((Control)node).setTooltip(newVal);
        return;
      }
      if (newVal instanceof JFXTooltip) {
        ((Control)node).setTooltip(null);
      }
    }
    if (newVal instanceof JFXTooltip) {
      install(node,newVal);
    }
 else {
      ((Control)node).setTooltip(newVal);
    }
  }
 else {
    uninstall(node,oldVal);
    install(node,newVal);
  }
}

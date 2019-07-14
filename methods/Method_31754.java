static JComponent fixedSize(JComponent component){
  component.setMaximumSize(component.getPreferredSize());
  return component;
}

static JComponent fixedHeight(JComponent component){
  Dimension dim=component.getMaximumSize();
  dim.height=component.getPreferredSize().height;
  component.setMaximumSize(dim);
  return component;
}

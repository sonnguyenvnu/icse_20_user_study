public static int getTabbIndex(JComponent component){
  Container tabb=component.getParent();
  Component lastComponent=component;
  while (!(tabb instanceof JTabbedPane)) {
    lastComponent=tabb;
    tabb=tabb.getParent();
  }
  return ((JTabbedPane)tabb).indexOfComponent(lastComponent);
}

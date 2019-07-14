private void setExpandListener(Component component,MouseListener expandListener){
  if (!(component instanceof JButton)) {
    component.addMouseListener(expandListener);
    if (component instanceof Container) {
      for (      Component child : ((Container)component).getComponents()) {
        setExpandListener(child,expandListener);
      }
    }
  }
}

private void initialize(final JComponent component){
  this.component=component;
  setUseDaemonThread(true);
  component.addAncestorListener(this);
}

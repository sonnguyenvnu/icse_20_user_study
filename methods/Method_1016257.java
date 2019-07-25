public static ComponentUpdater install(final JComponent component,final long delay,final ActionListener listener){
  uninstall(component);
  return new ComponentUpdater(component,delay,listener);
}

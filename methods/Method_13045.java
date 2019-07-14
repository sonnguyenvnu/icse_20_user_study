@Override @SuppressWarnings("unchecked") public <T extends JComponent & UriGettable>T make(API api,Container container){
  return (T)new ContainerPanel(api,container);
}

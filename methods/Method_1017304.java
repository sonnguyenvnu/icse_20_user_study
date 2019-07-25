private static SymfonyCreateService prepare(@Nullable Component component,@NotNull SymfonyCreateService service){
  service.init();
  service.setTitle("Symfony: Service Generator");
  service.setIconImage(Symfony2Icons.getImage(Symfony2Icons.SYMFONY));
  service.pack();
  service.setMinimumSize(new Dimension(550,250));
  if (component != null) {
    service.setLocationRelativeTo(component);
  }
  service.setVisible(true);
  return service;
}

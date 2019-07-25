public static void create(@NotNull Component c,@NotNull Project project,@NotNull String className){
  SymfonyJavascriptServiceNameForm dialog=new SymfonyJavascriptServiceNameForm(project,className);
  dialog.setMinimumSize(new Dimension(500,400));
  dialog.setTitle("Symfony: Service Generator - JavaScript Naming");
  dialog.pack();
  dialog.setIconImage(Symfony2Icons.getImage(Symfony2Icons.SYMFONY));
  dialog.setLocationRelativeTo(c);
  dialog.setVisible(true);
}

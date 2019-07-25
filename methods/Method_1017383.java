public static void open(@NotNull Component relativeTo){
  TwigJsonExampleDialog twigJsonExampleForm=new TwigJsonExampleDialog();
  twigJsonExampleForm.setTitle("Example: ide-twig.json");
  twigJsonExampleForm.setIconImage(Symfony2Icons.getImage(Symfony2Icons.SYMFONY));
  twigJsonExampleForm.pack();
  twigJsonExampleForm.setLocationRelativeTo(relativeTo);
  twigJsonExampleForm.setVisible(true);
}

protected void write(@NotNull final Project project,@NotNull final PhpClass phpClass,@NotNull final String className){
  new WriteCommandAction(project){
    @Override protected void run(    @NotNull Result result) throws Throwable {
      PsiElement bundleFile=null;
      try {
        String name=className;
        if (name.endsWith("Extension")) {
          name=name.substring(0,name.length() - "Extension".length());
        }
        final String finalName=name;
        bundleFile=PhpBundleFileFactory.createBundleFile(phpClass,"twig_extension","Twig\\Extension\\" + className,new HashMap<String,String>(){
{
            put("name",fr.adrienbrault.idea.symfony2plugin.util.StringUtils.underscore(finalName));
          }
        }
);
      }
 catch (      Exception e) {
        JOptionPane.showMessageDialog(null,"Error:" + e.getMessage());
        return;
      }
      if (bundleFile != null) {
        new OpenFileDescriptor(getProject(),bundleFile.getContainingFile().getVirtualFile(),0).navigate(true);
      }
    }
    @Override public String getGroupID(){
      return "Create Twig Extension";
    }
  }
.execute();
}

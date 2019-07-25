@Override protected void write(@NotNull final Project project,@NotNull final PhpClass phpClass,@NotNull final String className){
  new WriteCommandAction(project){
    @Override protected void run(    @NotNull Result result) throws Throwable {
      String fileTemplate="form_type_defaults";
      if (PhpElementsUtil.getClassMethod(project,"\\Symfony\\Component\\Form\\AbstractType","configureOptions") != null) {
        fileTemplate="form_type_configure";
      }
      PsiElement bundleFile=null;
      try {
        bundleFile=PhpBundleFileFactory.createBundleFile(phpClass,fileTemplate,"Form\\" + className,new HashMap<String,String>(){
{
            put("name",fr.adrienbrault.idea.symfony2plugin.util.StringUtils.underscore(phpClass.getName() + className));
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
      return "Create FormType";
    }
  }
.execute();
}

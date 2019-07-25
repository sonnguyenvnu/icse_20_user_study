/** 
 * Creates a new file using  {@link IgnoreTemplatesFactory#createFromTemplate(PsiDirectory)} to fill it with content.
 * @return created file
 */
@Override protected PsiFile compute(){
  IgnoreTemplatesFactory factory=new IgnoreTemplatesFactory(fileType);
  return factory.createFromTemplate(directory);
}

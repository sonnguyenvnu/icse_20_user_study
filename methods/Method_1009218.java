/** 
 * Logs error  {@link com.intellij.openapi.diagnostic.Logger} instance with the given {@code userMessage} and thetext of  {@code element} as the details and containing file of {@code element} as an * attachment
 * @param logger      logger to which to log an error.
 * @param userMessage User message for{@link com.intellij.diagnostic.LogMessageEx#createEvent(String,String,Attachment)}
 * @param element     element responsible for the error
 */
public static void error(@NotNull com.intellij.openapi.diagnostic.Logger logger,@NotNull String userMessage,@NotNull PsiElement element){
  PsiFile containingFile=element.getContainingFile();
  String fullUserMessage=fullUserMessage(userMessage,containingFile,element);
  String details=Joiner.on("\n").join(new Throwable().getStackTrace());
  Collection<Attachment> attachmentCollection;
  VirtualFile virtualFile=containingFile.getVirtualFile();
  if (virtualFile != null) {
    attachmentCollection=Collections.singletonList(AttachmentFactory.createAttachment(virtualFile));
  }
 else {
    attachmentCollection=Collections.emptyList();
  }
  logger.error(LogMessageEx.createEvent(fullUserMessage,details,fullUserMessage,null,attachmentCollection));
}

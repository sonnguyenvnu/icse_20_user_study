/** 
 * ?? Android ????
 * @param project
 * @param f1
 * @param f2
 */
public static void diff(final Project project,final File f1,final File f2){
  ApplicationManager.getApplication().invokeLater(new Runnable(){
    @Override public void run(){
      try {
        VirtualFile v1=LocalFileSystem.getInstance().refreshAndFindFileByIoFile(f1);
        Document document1=FileDocumentManager.getInstance().getDocument(v1);
        FileDocumentContentImpl fileDocumentContent1=new FileDocumentContentImpl(project,document1,v1);
        VirtualFile v2=LocalFileSystem.getInstance().refreshAndFindFileByIoFile(f2);
        Document document2=FileDocumentManager.getInstance().getDocument(v2);
        FileDocumentContentImpl fileDocumentContent2=new FileDocumentContentImpl(project,document2,v2);
        SimpleDiffRequest simpleDiffRequest=new SimpleDiffRequest(Constant.TITLE,fileDocumentContent1,fileDocumentContent2,f1.getName(),f2.getName());
        DiffManager.getInstance().showDiff(project,simpleDiffRequest);
      }
 catch (      Exception e) {
        NotificationUtils.errorNotification("Diff Source Error:" + e.getMessage());
      }
    }
  }
);
}

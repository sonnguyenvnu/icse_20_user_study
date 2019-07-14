/** 
 * Checks whether path for bookmark exists If path is not found, empty directory is created
 */
public static void checkForPath(Context context,String path,boolean isRootExplorer){
  if (!new File(path).exists()) {
    Toast.makeText(context,context.getString(R.string.bookmark_lost),Toast.LENGTH_SHORT).show();
    Operations.mkdir(RootHelper.generateBaseFile(new File(path),true),context,isRootExplorer,new Operations.ErrorCallBack(){
      @Override public void exists(      HybridFile file){
      }
      @Override public void launchSAF(      HybridFile file){
      }
      @Override public void launchSAF(      HybridFile file,      HybridFile file1){
      }
      @Override public void done(      HybridFile hFile,      boolean b){
      }
      @Override public void invalidName(      HybridFile file){
      }
    }
);
  }
}

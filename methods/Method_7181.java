@UiThread public static void prepareSendingDocument(String path,String originalPath,Uri uri,String caption,String mine,long dialog_id,MessageObject reply_to_msg,InputContentInfoCompat inputContent,MessageObject editingMessageObject){
  if ((path == null || originalPath == null) && uri == null) {
    return;
  }
  ArrayList<String> paths=new ArrayList<>();
  ArrayList<String> originalPaths=new ArrayList<>();
  ArrayList<Uri> uris=null;
  if (uri != null) {
    uris=new ArrayList<>();
    uris.add(uri);
  }
  if (path != null) {
    paths.add(path);
    originalPaths.add(originalPath);
  }
  prepareSendingDocuments(paths,originalPaths,uris,caption,mine,dialog_id,reply_to_msg,inputContent,editingMessageObject);
}

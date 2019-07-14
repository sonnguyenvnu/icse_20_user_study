private void triggerStorageAccessFramework(){
  Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
  mainActivity.startActivityForResult(intent,3);
}

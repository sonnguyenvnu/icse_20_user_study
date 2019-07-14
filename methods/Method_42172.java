private void scanFolder(String path){
  String[] list=new File(path).list(new ImageFileFilter(true));
  if (list != null)   MediaHelper.scanFile(getApplicationContext(),list);
}

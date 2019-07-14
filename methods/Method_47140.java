public void hide(String path){
  dataUtils.addHiddenFile(path);
  File file=new File(path);
  if (file.isDirectory()) {
    File f1=new File(path + "/" + ".nomedia");
    if (!f1.exists()) {
      try {
        getMainActivity().mainActivityHelper.mkFile(new HybridFile(OpenMode.FILE,f1.getPath()),this);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
    FileUtils.scanFile(file,getActivity());
  }
}

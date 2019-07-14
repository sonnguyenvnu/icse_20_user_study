public void deleteFiles(ArrayList<HybridFileParcelable> files){
  if (files == null || files.size() == 0)   return;
  if (files.get(0).isSmb()) {
    new DeleteTask(mainActivity).execute((files));
    return;
  }
  int mode=checkFolder(new File(files.get(0).getPath()).getParentFile(),mainActivity);
  if (mode == 2) {
    mainActivity.oparrayList=(files);
    mainActivity.operation=DataUtils.DELETE;
  }
 else   if (mode == 1 || mode == 0)   new DeleteTask(mainActivity).execute((files));
 else   Toast.makeText(mainActivity,R.string.not_allowed,Toast.LENGTH_SHORT).show();
}

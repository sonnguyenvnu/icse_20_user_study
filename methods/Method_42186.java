private void displayContentFolder(File dir){
  canGoBack=false;
  if (dir.canRead()) {
    folders=new ArrayList<>();
    File parent=dir.getParentFile();
    if (parent.canRead()) {
      canGoBack=true;
      folders.add(0,parent);
    }
    File[] files=dir.listFiles(new FoldersFileFilter());
    if (files != null && files.length > 0) {
      folders.addAll(new ArrayList<>(Arrays.asList(files)));
      currentFolderPath.setText(dir.getAbsolutePath());
    }
    currentFolderPath.setText(dir.getAbsolutePath());
    adapter.notifyDataSetChanged();
  }
}

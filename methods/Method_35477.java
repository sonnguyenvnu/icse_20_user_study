public static List<Folder> generateDefaultFolders(){
  List<Folder> defaultFolders=new ArrayList<>(3);
  File downloadDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
  File musicDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
  defaultFolders.add(FileUtils.folderFromDir(downloadDir));
  defaultFolders.add(FileUtils.folderFromDir(musicDir));
  return defaultFolders;
}

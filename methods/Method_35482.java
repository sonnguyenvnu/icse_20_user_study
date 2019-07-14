public static Folder folderFromDir(File dir){
  Folder folder=new Folder(dir.getName(),dir.getAbsolutePath());
  List<Song> songs=musicFiles(dir);
  folder.setSongs(songs);
  folder.setNumOfSongs(songs.size());
  return folder;
}

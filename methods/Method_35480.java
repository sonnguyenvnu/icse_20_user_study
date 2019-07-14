public static List<Song> musicFiles(File dir){
  List<Song> songs=new ArrayList<>();
  if (dir != null && dir.isDirectory()) {
    final File[] files=dir.listFiles(new FileFilter(){
      @Override public boolean accept(      File item){
        return item.isFile() && isMusic(item);
      }
    }
);
    for (    File file : files) {
      Song song=fileToMusic(file);
      if (song != null) {
        songs.add(song);
      }
    }
  }
  if (songs.size() > 1) {
    Collections.sort(songs,new Comparator<Song>(){
      @Override public int compare(      Song left,      Song right){
        return left.getTitle().compareTo(right.getTitle());
      }
    }
);
  }
  return songs;
}

private Song cursorToMusic(Cursor cursor){
  String realPath=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
  File songFile=new File(realPath);
  Song song;
  if (songFile.exists()) {
    song=FileUtils.fileToMusic(songFile);
    if (song != null) {
      return song;
    }
  }
  song=new Song();
  song.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
  String displayName=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
  if (displayName.endsWith(".mp3")) {
    displayName=displayName.substring(0,displayName.length() - 4);
  }
  song.setDisplayName(displayName);
  song.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
  song.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
  song.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
  song.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
  song.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
  return song;
}

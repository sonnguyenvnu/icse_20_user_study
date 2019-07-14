public static Song fileToMusic(File file){
  if (file.length() == 0)   return null;
  MediaMetadataRetriever metadataRetriever=new MediaMetadataRetriever();
  metadataRetriever.setDataSource(file.getAbsolutePath());
  final int duration;
  String keyDuration=metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
  if (keyDuration == null || !keyDuration.matches("\\d+"))   return null;
  duration=Integer.parseInt(keyDuration);
  final String title=extractMetadata(metadataRetriever,MediaMetadataRetriever.METADATA_KEY_TITLE,file.getName());
  final String displayName=extractMetadata(metadataRetriever,MediaMetadataRetriever.METADATA_KEY_TITLE,file.getName());
  final String artist=extractMetadata(metadataRetriever,MediaMetadataRetriever.METADATA_KEY_ARTIST,UNKNOWN);
  final String album=extractMetadata(metadataRetriever,MediaMetadataRetriever.METADATA_KEY_ALBUM,UNKNOWN);
  final Song song=new Song();
  song.setTitle(title);
  song.setDisplayName(displayName);
  song.setArtist(artist);
  song.setPath(file.getAbsolutePath());
  song.setAlbum(album);
  song.setDuration(duration);
  song.setSize((int)file.length());
  return song;
}

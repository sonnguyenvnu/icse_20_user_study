private MediaMetadataCompat makeMediaMetadata(Music music,Music.Track track,int index){
  MediaMetadataCompat.Builder builder=new MediaMetadataCompat.Builder().putString(MediaMetadataCompat.METADATA_KEY_TITLE,track.title).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE,track.title);
  if (!music.artists.isEmpty()) {
    String artists=StringCompat.join(getString(R.string.item_information_delimiter_slash),music.getArtistNames());
    builder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST,artists).putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST,artists).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE,artists);
  }
  if (track.duration > 0) {
    int duration=track.duration * 1000;
    builder.putLong(MediaMetadataCompat.METADATA_KEY_DURATION,duration);
  }
  builder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM,music.title).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION,music.title);
  String date=music.getReleaseDate();
  if (!TextUtils.isEmpty(date)) {
    builder.putString(MediaMetadataCompat.METADATA_KEY_DATE,date);
    if (date.length() > 4) {
      try {
        long year=Long.parseLong(date.substring(0,4));
        builder.putLong(MediaMetadataCompat.METADATA_KEY_YEAR,year);
      }
 catch (      NumberFormatException e) {
        e.printStackTrace();
      }
    }
  }
  String genre=CollectionUtils.firstOrNull(music.genres);
  if (!TextUtils.isEmpty(genre)) {
    builder.putString(MediaMetadataCompat.METADATA_KEY_GENRE,genre);
  }
  builder.putLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER,index).putLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS,music.tracks.size());
  String albumArtUri=music.cover.getLargeUrl();
  builder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI,albumArtUri).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI,albumArtUri);
  if (music.rating != null) {
    float starRating=music.rating.value / music.rating.max * 5;
    starRating=Math.max(0,Math.min(5,starRating));
    RatingCompat rating=RatingCompat.newStarRating(RatingCompat.RATING_5_STARS,starRating);
    builder.putRating(MediaMetadataCompat.METADATA_KEY_RATING,rating);
  }
  String mediaId=music.id + "#" + index;
  builder.putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID,mediaId).putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI,track.previewUrl);
  return builder.build();
}

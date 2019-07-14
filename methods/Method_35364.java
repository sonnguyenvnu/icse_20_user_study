@Override public void bind(Song song,int position){
  textViewIndex.setText(String.valueOf(position + 1));
  textViewName.setText(song.getDisplayName());
  textViewInfo.setText(String.format("%s | %s",TimeUtils.formatDuration(song.getDuration()),song.getArtist()));
}

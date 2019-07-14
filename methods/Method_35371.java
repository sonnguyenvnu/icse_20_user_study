@Override public void bind(Song song,int position){
  textViewName.setText(song.getDisplayName());
  textViewArtist.setText(song.getArtist());
  textViewDuration.setText(TimeUtils.formatDuration(song.getDuration()));
}

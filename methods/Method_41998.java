@Override public boolean onCreateOptionsMenu(Menu menu){
  getMenuInflater().inflate(org.horaapps.leafpic.R.menu.menu_video_player,menu);
  MappedTrackInfo mappedTrackInfo=trackSelector.getCurrentMappedTrackInfo();
  if (player != null && mappedTrackInfo != null) {
    for (int i=0; i < mappedTrackInfo.length; i++) {
      TrackGroupArray trackGroups=mappedTrackInfo.getTrackGroups(i);
      if (trackGroups.length != 0) {
switch (player.getRendererType(i)) {
case C.TRACK_TYPE_AUDIO:
          menu.findItem(R.id.audio_stuff).setVisible(true);
        audio=i;
      break;
case C.TRACK_TYPE_VIDEO:
    menu.findItem(R.id.video_stuff).setVisible(true);
  video=i;
break;
case C.TRACK_TYPE_TEXT:
menu.findItem(R.id.text_stuff).setVisible(true);
text=i;
break;
}
}
}
}
return true;
}

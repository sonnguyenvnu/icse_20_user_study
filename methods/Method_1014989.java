private void replay(){
  try {
    this._mediaPlayer.reset();
    this._mediaPlayer.setAudioStreamType(0);
    this._mediaPlayer.setVolume(1.0F,1.0F);
    this._mediaPlayer.setDataSource(this.context,this._playingUri);
    this._mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
      public void onPrepared(      MediaPlayer mp){
        mp.start();
      }
    }
);
    this._mediaPlayer.prepareAsync();
  }
 catch (  IOException var2) {
    var2.printStackTrace();
  }
}

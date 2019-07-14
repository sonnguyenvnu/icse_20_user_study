public void onVideoCompleted(){
  if (controlsView instanceof MiniControlsView) {
    MiniControlsView miniControlsView=(MiniControlsView)controlsView;
    miniControlsView.isCompleted=true;
    miniControlsView.progress=0;
    miniControlsView.bufferedPosition=0;
    miniControlsView.updatePlayButton();
    miniControlsView.invalidate();
    miniControlsView.show(true,true);
  }
}

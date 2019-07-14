public void updatePlayButton(){
  if (controlsView instanceof MiniControlsView) {
    MiniControlsView miniControlsView=(MiniControlsView)controlsView;
    miniControlsView.updatePlayButton();
    miniControlsView.invalidate();
  }
}

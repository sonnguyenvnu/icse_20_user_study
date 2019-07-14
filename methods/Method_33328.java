private void delegateToTrack(MouseEvent event){
  if (!event.isConsumed()) {
    event.consume();
    track.fireEvent(event);
  }
}

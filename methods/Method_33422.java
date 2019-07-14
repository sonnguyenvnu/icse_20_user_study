public static void addPressAndHoldFilter(Node node,Duration holdTime,EventHandler<MouseEvent> handler){
  Wrapper<MouseEvent> eventWrapper=new Wrapper<>();
  PauseTransition holdTimer=new PauseTransition(holdTime);
  holdTimer.setOnFinished(event -> handler.handle(eventWrapper.content));
  node.addEventFilter(MouseEvent.MOUSE_PRESSED,event -> {
    eventWrapper.content=event;
    holdTimer.playFromStart();
  }
);
  node.addEventFilter(MouseEvent.MOUSE_RELEASED,event -> holdTimer.stop());
  node.addEventFilter(MouseEvent.DRAG_DETECTED,event -> holdTimer.stop());
}

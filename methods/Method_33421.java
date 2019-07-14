public static void addPressAndHoldHandler(Node node,Duration holdTime,EventHandler<MouseEvent> handler){
  Wrapper<MouseEvent> eventWrapper=new Wrapper<>();
  PauseTransition holdTimer=new PauseTransition(holdTime);
  holdTimer.setOnFinished(event -> handler.handle(eventWrapper.content));
  node.addEventHandler(MouseEvent.MOUSE_PRESSED,event -> {
    eventWrapper.content=event;
    holdTimer.playFromStart();
  }
);
  node.addEventHandler(MouseEvent.MOUSE_RELEASED,event -> holdTimer.stop());
  node.addEventHandler(MouseEvent.DRAG_DETECTED,event -> holdTimer.stop());
}

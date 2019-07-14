protected void dequeueEvents(){
synchronized (eventQueueDequeueLock) {
    while (!eventQueue.isEmpty()) {
      Event e=eventQueue.remove();
switch (e.getFlavor()) {
case Event.MOUSE:
        handleMouseEvent((MouseEvent)e);
      break;
case Event.KEY:
    handleKeyEvent((KeyEvent)e);
  break;
}
}
}
}

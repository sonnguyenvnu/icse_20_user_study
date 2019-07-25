@Override public void process(){
switch (eventType) {
case KEY_PRESSED:
    if (canvas instanceof GameCanvas) {
      ((GameCanvas)canvas).gameKeyPressed(keyCode);
    }
 else {
      canvas.keyPressed(keyCode);
    }
  break;
case KEY_REPEATED:
if (canvas instanceof GameCanvas) {
  ((GameCanvas)canvas).gameKeyRepeated(keyCode);
}
 else {
  canvas.keyRepeated(keyCode);
}
break;
case KEY_RELEASED:
if (canvas instanceof GameCanvas) {
((GameCanvas)canvas).gameKeyReleased(keyCode);
}
 else {
canvas.keyReleased(keyCode);
}
break;
case POINTER_PRESSED:
canvas.pointerPressed(pointer,x,y);
break;
case POINTER_DRAGGED:
canvas.pointerDragged(pointer,x,y);
break;
case POINTER_RELEASED:
canvas.pointerReleased(pointer,x,y);
break;
case SHOW_NOTIFY:
canvas.showNotify();
break;
case HIDE_NOTIFY:
canvas.hideNotify();
break;
case SIZE_CHANGED:
canvas.sizeChanged(width,height);
break;
}
}

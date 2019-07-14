public void updateDisplay(){
  flush();
  beginPixelsOp(OP_WRITE);
  drawTexture();
  endPixelsOp();
}

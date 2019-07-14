public TtmlStyle setLinethrough(boolean linethrough){
  Assertions.checkState(inheritableStyle == null);
  this.linethrough=linethrough ? ON : OFF;
  return this;
}

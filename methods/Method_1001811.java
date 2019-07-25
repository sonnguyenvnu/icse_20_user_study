private void init(StringBounder stringBounder){
  if (middle != null) {
    return;
  }
  final Real first=tileArguments.getFirstLivingSpace().getPosC(stringBounder);
  final Component comp=getComponent(stringBounder);
  final Real last=tileArguments.getLastLivingSpace().getPosC(stringBounder);
  this.middle=RealUtils.middle(first,last);
}

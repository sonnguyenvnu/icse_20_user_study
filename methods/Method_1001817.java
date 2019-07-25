private void init(StringBounder stringBounder){
  if (first != null) {
    return;
  }
  for (  Participant p : reference.getParticipant()) {
    final LivingSpace livingSpace=tileArguments.getLivingSpace(p);
    final Real pos=livingSpace.getPosC(stringBounder);
    if (first == null || pos.getCurrentValue() < first.getCurrentValue()) {
      this.first=livingSpace.getPosB();
    }
    if (last == null || pos.getCurrentValue() > last.getCurrentValue()) {
      this.last=livingSpace.getPosD(stringBounder);
    }
  }
  final Component comp=getComponent(stringBounder);
  final Dimension2D dim=comp.getPreferredDimension(stringBounder);
  if (reference.getParticipant().size() == 1) {
    this.last=this.last.addAtLeast(0);
  }
  this.last.ensureBiggerThan(this.first.addFixed(dim.getWidth()));
}

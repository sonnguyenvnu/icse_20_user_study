@Override public void paint(Graphics graphics,EditorComponent editor,EditorCell cell){
  if (!(myHighlighted)) {
    return;
  }
  if (isDirectCell(cell)) {
    ((jetbrains.mps.nodeEditor.cells.EditorCell)cell).paintSelection(graphics,getColor(),false);
    repaintConflictedMessages(graphics,cell);
  }
 else {
    if (myMessageTarget.getTarget() == MessageTargetEnum.DELETED_CHILD) {
      drawDeletedChild(graphics,cell);
    }
 else {
      Rectangle bounds=(isIndirectRoot(editor) ? getFirstPseudoLineBounds(editor) : GeometryUtil.getBounds(cell));
      graphics.setColor(ChangeColors.get((isConflicted() ? ChangeType.CONFLICTED : ChangeType.CHANGE)));
      graphics.drawRect(bounds.x + 1,bounds.y + 1,bounds.width - 2,bounds.height - 2);
    }
  }
}

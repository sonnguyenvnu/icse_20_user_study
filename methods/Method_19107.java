/** 
 * @return a Rect containing the bounds of the views inside theSection represented by this node.
 */
public Rect getBounds(){
  List<?> children=getSectionChildren();
  int count=children.size();
  if (count == 0) {
    return null;
  }
  Rect rect=new Rect();
  if (isDiffSectionSpec()) {
    View firstView=(View)children.get(0);
    View lastView=(View)children.get(count - 1);
    rect.left=firstView.getLeft();
    rect.top=firstView.getTop();
    rect.right=lastView.getRight();
    rect.bottom=lastView.getBottom();
  }
 else {
    DebugSection firstSection=(DebugSection)children.get(0);
    DebugSection lastSection=(DebugSection)children.get(count - 1);
    Rect first=firstSection.getBounds();
    Rect last=lastSection.getBounds();
    rect.left=first.left;
    rect.top=first.top;
    rect.right=last.right;
    rect.bottom=last.bottom;
  }
  return rect;
}

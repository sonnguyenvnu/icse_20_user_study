/** 
 * Returns whether breadcrumb element contains specified point or not.
 * @param element breadcrumb element
 * @param x       point X coordinate
 * @param y       point Y coordinate
 * @return true if breadcrumb element contains specified point, false otherwise
 */
public static boolean contains(final JComponent element,final int x,final int y){
  final int w=element.getWidth();
  final int h=element.getHeight();
  final Container container=element.getParent();
  if (container != null && container instanceof WebBreadcrumb && element instanceof BreadcrumbElement) {
    final WebBreadcrumb breadcrumb=(WebBreadcrumb)container;
    final BreadcrumbElementType type=BreadcrumbElementType.getType(element,breadcrumb);
    final int overlap=breadcrumb.getElementOverlap();
    final int shadeWidth=breadcrumb.getShadeWidth();
    final int round=breadcrumb.getRound();
    final boolean encloseLast=breadcrumb.isEncloseLastElement();
    final boolean ltr=element.getComponentOrientation().isLeftToRight();
    return getFillShape(element,type,w,h,overlap,shadeWidth,round,encloseLast,ltr).contains(x,y);
  }
  return 0 < x && x < w && 0 < y && y < h;
}

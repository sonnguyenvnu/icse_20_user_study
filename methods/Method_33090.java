/** 
 * @return a list of sides that corresponds to the current drawers order
 */
public List<Side> getOpenedDrawersOrder(){
  List<Side> order=new ArrayList<>();
  for (int i=0, drawersSize=drawers.size(); i < drawersSize; i++) {
    final JFXDrawer jfxDrawer=drawers.get(i);
    if (jfxDrawer.isOpened()) {
      order.add(Side.valueOf(jfxDrawer.getDirection().toString()));
    }
  }
  return order;
}

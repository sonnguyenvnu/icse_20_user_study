/** 
 * this method is used to add shadow effect to the node, however the shadow is not real ( gets affected with node transformations) <p> use  {@link #createMaterialNode(Node,int)} instead to generate a real shadow
 */
public static void setDepth(Node control,int level){
  level=level < 0 ? 0 : level;
  level=level > 5 ? 5 : level;
  control.setEffect(new DropShadow(BlurType.GAUSSIAN,depth[level].getColor(),depth[level].getRadius(),depth[level].getSpread(),depth[level].getOffsetX(),depth[level].getOffsetY()));
}

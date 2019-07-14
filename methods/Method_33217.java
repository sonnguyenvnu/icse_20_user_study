/** 
 * this method will generate a new container node that prevent control transformation to be applied to the shadow effect (which makes it looks as a real shadow)
 */
public static Node createMaterialNode(Node control,int level){
  Node container=new Pane(control){
    @Override protected double computeMaxWidth(    double height){
      return computePrefWidth(height);
    }
    @Override protected double computeMaxHeight(    double width){
      return computePrefHeight(width);
    }
    @Override protected double computePrefWidth(    double height){
      return control.prefWidth(height);
    }
    @Override protected double computePrefHeight(    double width){
      return control.prefHeight(width);
    }
  }
;
  container.getStyleClass().add("depth-container");
  container.setPickOnBounds(false);
  level=level < 0 ? 0 : level;
  level=level > 5 ? 5 : level;
  container.setEffect(new DropShadow(BlurType.GAUSSIAN,depth[level].getColor(),depth[level].getRadius(),depth[level].getSpread(),depth[level].getOffsetX(),depth[level].getOffsetY()));
  return container;
}

/** 
 * Sets layouts width and height
 * @param width the layout width
 * @param height the layout height
 */
private void layout(int width,int height){
  int itemsWidth=width - 2 * PADDING;
  itemsLayout.layout(0,0,itemsWidth,height);
}

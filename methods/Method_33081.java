private void updateContent(){
  paddingPane.setPrefSize(0,0);
  paddingPane.setMinSize(0,0);
  Node contentNode=content;
switch (getDirection()) {
case TOP:
    contentNode=new VBox(paddingPane,content);
  VBox.setVgrow(content,Priority.ALWAYS);
break;
case BOTTOM:
contentNode=new VBox(content,paddingPane);
VBox.setVgrow(content,Priority.ALWAYS);
break;
case LEFT:
contentNode=new HBox(paddingPane,content);
HBox.setHgrow(content,Priority.ALWAYS);
break;
case RIGHT:
contentNode=new HBox(content,paddingPane);
HBox.setHgrow(content,Priority.ALWAYS);
break;
}
contentNode.setPickOnBounds(false);
if (isOpened()) {
paddingSizeProperty.set(computePaddingSize());
}
contentHolder.getChildren().setAll(contentNode);
}

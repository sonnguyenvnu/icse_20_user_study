private void updatePlaceholder(Node newView){
  if (view.getWidth() > 0 && view.getHeight() > 0) {
    SnapshotParameters parameters=new SnapshotParameters();
    parameters.setFill(Color.TRANSPARENT);
    Image placeholderImage=view.snapshot(parameters,new WritableImage((int)view.getWidth(),(int)view.getHeight()));
    placeholder.setImage(placeholderImage);
    placeholder.setFitWidth(placeholderImage.getWidth());
    placeholder.setFitHeight(placeholderImage.getHeight());
  }
 else {
    placeholder.setImage(null);
  }
  placeholder.setVisible(true);
  placeholder.setOpacity(1.0);
  view.getChildren().setAll(placeholder,newView);
  placeholder.toFront();
}

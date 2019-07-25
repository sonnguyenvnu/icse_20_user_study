public void show(DocumentViewModel document){
  flow=VirtualFlow.createVertical(document.getPages(),DocumentViewerPage::new);
  getChildren().setAll(flow);
  flow.visibleCells().addListener((ListChangeListener<? super DocumentViewerPage>)c -> updateCurrentPage(flow.visibleCells()));
  flow.estimatedScrollYProperty().addListener((observable,oldValue,newValue) -> scrollY.setValue(newValue));
  scrollY.addListener((observable,oldValue,newValue) -> flow.estimatedScrollYProperty().setValue((double)newValue));
  flow.totalLengthEstimateProperty().addListener((observable,oldValue,newValue) -> scrollYMax.setValue(newValue));
}

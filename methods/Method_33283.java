protected BorderPane createCalendarArrowsPane(){
  SVGGlyph leftChevron=new SVGGlyph(0,"CHEVRON_LEFT","M 742,-37 90,614 Q 53,651 53,704.5 53,758 90,795 l 652,651 q 37,37 90.5,37 53.5,0 90.5,-37 l 75,-75 q 37,-37 37,-90.5 0,-53.5 -37,-90.5 L 512,704 998,219 q 37,-38 37,-91 0,-53 -37,-90 L 923,-37 Q 886,-74 832.5,-74 779,-74 742,-37 z",Color.GRAY);
  SVGGlyph rightChevron=new SVGGlyph(0,"CHEVRON_RIGHT","m 1099,704 q 0,-52 -37,-91 L 410,-38 q -37,-37 -90,-37 -53,0 -90,37 l -76,75 q -37,39 -37,91 0,53 37,90 l 486,486 -486,485 q -37,39 -37,91 0,53 37,90 l 76,75 q 36,38 90,38 54,0 90,-38 l 652,-651 q 37,-37 37,-90 z",Color.GRAY);
  leftChevron.setFill(DEFAULT_COLOR);
  leftChevron.setSize(6,11);
  rightChevron.setFill(DEFAULT_COLOR);
  rightChevron.setSize(6,11);
  backMonthButton=new JFXButton();
  backMonthButton.setMinSize(40,40);
  backMonthButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,new CornerRadii(40),Insets.EMPTY)));
  backMonthButton.getStyleClass().add("left-button");
  backMonthButton.setGraphic(leftChevron);
  backMonthButton.setRipplerFill(this.datePicker.getDefaultColor());
  backMonthButton.setOnAction(t -> forward(-1,MONTHS,false,true));
  forwardMonthButton=new JFXButton();
  forwardMonthButton.setMinSize(40,40);
  forwardMonthButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,new CornerRadii(40),Insets.EMPTY)));
  forwardMonthButton.getStyleClass().add("right-button");
  forwardMonthButton.setGraphic(rightChevron);
  forwardMonthButton.setRipplerFill(this.datePicker.getDefaultColor());
  forwardMonthButton.setOnAction(t -> forward(1,MONTHS,false,true));
  BorderPane arrowsContainer=new BorderPane();
  arrowsContainer.setLeft(backMonthButton);
  arrowsContainer.setRight(forwardMonthButton);
  arrowsContainer.setPadding(new Insets(4,12,2,12));
  arrowsContainer.setPickOnBounds(false);
  return arrowsContainer;
}

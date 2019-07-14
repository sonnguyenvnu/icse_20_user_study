private StackPane createMinutesContent(LocalTime time){
  StackPane minsPointer=new StackPane();
  Circle selectionCircle=new Circle(contentCircleRadius / 6);
  selectionCircle.fillProperty().bind(timePicker.defaultColorProperty());
  Circle minCircle=new Circle(selectionCircle.getRadius() / 8);
  minCircle.setFill(Color.rgb(255,255,255,0.87));
  minCircle.setTranslateX(selectionCircle.getRadius() - minCircle.getRadius());
  minCircle.setVisible(time.getMinute() % 5 != 0);
  selectedMinLabel.textProperty().addListener((o,oldVal,newVal) -> {
    if (Integer.parseInt(newVal) % 5 == 0) {
      minCircle.setVisible(false);
    }
 else {
      minCircle.setVisible(true);
    }
  }
);
  double shift=9;
  Line line=new Line(shift,0,contentCircleRadius,0);
  line.fillProperty().bind(timePicker.defaultColorProperty());
  line.strokeProperty().bind(line.fillProperty());
  line.setStrokeWidth(1.5);
  minsPointer.getChildren().addAll(line,selectionCircle,minCircle);
  StackPane.setAlignment(selectionCircle,Pos.CENTER_LEFT);
  StackPane.setAlignment(minCircle,Pos.CENTER_LEFT);
  Group pointerGroup=new Group();
  pointerGroup.getChildren().add(minsPointer);
  pointerGroup.setTranslateX((-contentCircleRadius + shift) / 2);
  minsPointerRotate=new Rotate(0,contentCircleRadius - shift,selectionCircle.getRadius());
  pointerGroup.getTransforms().add(minsPointerRotate);
  Pane clockLabelsContainer=new Pane();
  double radius=contentCircleRadius - shift - selectionCircle.getRadius();
  for (int i=0; i < 12; i++) {
    StackPane labelContainer=new StackPane();
    int val=((i + 3) * 5) % 60;
    Label label=new Label(String.valueOf(unitConverter.toString(val)));
    label.setFont(Font.font(ROBOTO,FontWeight.BOLD,12));
    label.setTextFill(val == time.getMinute() ? Color.rgb(255,255,255,0.87) : Color.rgb(0,0,0,0.87));
    selectedMinLabel.textProperty().addListener((o,oldVal,newVal) -> {
      if (Integer.parseInt(newVal) == Integer.parseInt(label.getText())) {
        label.setTextFill(Color.rgb(255,255,255,0.87));
      }
 else {
        label.setTextFill(Color.rgb(0,0,0,0.87));
      }
    }
);
    labelContainer.getChildren().add(label);
    double labelSize=(selectionCircle.getRadius() / Math.sqrt(2)) * 2;
    labelContainer.setMinSize(labelSize,labelSize);
    double angle=2 * i * Math.PI / 12;
    double xOffset=radius * Math.cos(angle);
    double yOffset=radius * Math.sin(angle);
    final double startx=contentCircleRadius + xOffset;
    final double starty=contentCircleRadius + yOffset;
    labelContainer.setLayoutX(startx - labelContainer.getMinWidth() / 2);
    labelContainer.setLayoutY(starty - labelContainer.getMinHeight() / 2);
    clockLabelsContainer.getChildren().add(labelContainer);
  }
  minsPointerRotate.setAngle(180 + (time.getMinute() + 45) % 60 * Math.toDegrees(2 * Math.PI / 60));
  return new StackPane(pointerGroup,clockLabelsContainer);
}

private StackPane createHoursContent(LocalTime time,boolean _24HourView){
  StackPane hoursPointer=new StackPane(), _24HoursPointer=new StackPane();
  Circle selectionCircle=new Circle(contentCircleRadius / 6), _24HourSelectionCircle=new Circle(contentCircleRadius / 6);
  selectionCircle.fillProperty().bind(timePicker.defaultColorProperty());
  _24HourSelectionCircle.fillProperty().bind(timePicker.defaultColorProperty());
  double shift=9, _24HourShift=27.5;
  Line line=new Line(shift,0,contentCircleRadius,0);
  line.fillProperty().bind(timePicker.defaultColorProperty());
  line.strokeProperty().bind(line.fillProperty());
  line.setStrokeWidth(1.5);
  hoursPointer.getChildren().addAll(line,selectionCircle);
  StackPane.setAlignment(selectionCircle,Pos.CENTER_LEFT);
  Group pointerGroup=new Group();
  pointerGroup.getChildren().add(hoursPointer);
  pointerGroup.setTranslateX((-contentCircleRadius + shift) / 2);
  hoursPointerRotate=new Rotate(0,contentCircleRadius - shift,selectionCircle.getRadius());
  pointerRotate.set(hoursPointerRotate);
  pointerGroup.getTransforms().add(hoursPointerRotate);
  pointerGroup.setVisible(!is24HourView);
  Line _24HourLine=new Line(shift + _24HourShift,0,contentCircleRadius,0);
  _24HourLine.fillProperty().bind(timePicker.defaultColorProperty());
  _24HourLine.strokeProperty().bind(_24HourLine.fillProperty());
  _24HourLine.setStrokeWidth(1.5);
  _24HoursPointer.getChildren().addAll(_24HourLine,_24HourSelectionCircle);
  StackPane.setAlignment(_24HourSelectionCircle,Pos.CENTER_LEFT);
  Group pointer24HourGroup=new Group();
  pointer24HourGroup.getChildren().add(_24HoursPointer);
  pointer24HourGroup.setTranslateX(((-contentCircleRadius + shift) / 2) + (_24HourShift / 2));
  _24HourHoursPointerRotate=new Rotate(0,contentCircleRadius - shift - _24HourShift,selectionCircle.getRadius());
  _24HourPointerRotate.set(_24HourHoursPointerRotate);
  pointer24HourGroup.getTransforms().add(_24HourHoursPointerRotate);
  pointer24HourGroup.setVisible(is24HourView);
  Pane clockLabelsContainer=new Pane();
  double radius=contentCircleRadius - shift - selectionCircle.getRadius();
  for (int i=0; i < 12; i++) {
    int val=(i + 3) % 12 == 0 ? 12 : (i + 3) % 12;
    Label label=new Label(Integer.toString(val));
    label.setFont(Font.font(ROBOTO,FontWeight.BOLD,12));
    label.setTextFill(((val == time.getHour() % 12 || (val == 12 && time.getHour() % 12 == 0)) && !is24HourView) ? Color.rgb(255,255,255,0.87) : Color.rgb(0,0,0,0.87));
    selectedHourLabel.textProperty().addListener((o,oldVal,newVal) -> {
      if (Integer.parseInt(newVal) == Integer.parseInt(label.getText())) {
        label.setTextFill(Color.rgb(255,255,255,0.87));
      }
 else {
        label.setTextFill(Color.rgb(0,0,0,0.87));
      }
    }
);
    StackPane labelContainer=new StackPane();
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
    if (!is24HourView && (val == time.getHour() % 12 || (val == 12 && time.getHour() % 12 == 0))) {
      hoursPointerRotate.setAngle(180 + Math.toDegrees(angle));
    }
  }
  if (_24HourView) {
    radius/=1.6;
    for (int i=0; i < 12; i++) {
      int val=(i + 3) % 12 == 0 ? 12 : (i + 3) % 12;
      val+=(val == 12 ? -12 : 12);
      Label label=new Label(Integer.toString(val) + (val == 0 ? "0" : ""));
      label.setFont(Font.font(ROBOTO,FontWeight.NORMAL,10));
      label.setTextFill((val == time.getHour() % 24 || (val == 0 && time.getHour() % 24 == 0) && is24HourView) ? Color.rgb(255,255,255,0.54) : Color.rgb(0,0,0,0.54));
      selectedHourLabel.textProperty().addListener((o,oldVal,newVal) -> {
        if (Integer.parseInt(newVal) == Integer.parseInt(label.getText())) {
          label.setTextFill(Color.rgb(255,255,255,0.54));
        }
 else {
          label.setTextFill(Color.rgb(0,0,0,0.54));
        }
      }
);
      StackPane labelContainer=new StackPane();
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
      if (val == time.getHour() % 24 || (val == 24 && time.getHour() % 24 == 0)) {
        _24HourHoursPointerRotate.setAngle(180 + Math.toDegrees(angle));
      }
    }
  }
  if (_24HourView) {
    return new StackPane(pointerGroup,pointer24HourGroup,clockLabelsContainer);
  }
 else {
    return new StackPane(pointerGroup,clockLabelsContainer);
  }
}

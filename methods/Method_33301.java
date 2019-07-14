protected void createDayCells(){
  for (int row=0; row < 6; row++) {
    for (int col=0; col < daysPerWeek; col++) {
      DateCell dayCell=createDayCell();
      dayCell.addEventHandler(MouseEvent.MOUSE_CLICKED,click -> {
        if (click.getButton() != MouseButton.PRIMARY) {
          return;
        }
        DateCell selectedDayCell=(DateCell)click.getSource();
        selectDayCell(selectedDayCell);
        currentFocusedDayCell=selectedDayCell;
      }
);
      dayCell.setOnMouseEntered((event) -> {
        if (!dayCell.getStyleClass().contains("selected")) {
          dayCell.setBackground(new Background(new BackgroundFill(Color.valueOf("#EDEDED"),new CornerRadii(40),Insets.EMPTY)));
        }
      }
);
      dayCell.setOnMouseExited((event) -> {
        if (!dayCell.getStyleClass().contains("selected")) {
          dayCell.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
        }
      }
);
      dayCell.setAlignment(Pos.BASELINE_CENTER);
      dayCell.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5))));
      dayCell.setFont(Font.font(ROBOTO,FontWeight.BOLD,12));
      dayCells.add(dayCell);
    }
  }
  dayCellDates=new LocalDate[6 * daysPerWeek];
  updateContentGrid();
}

protected void forward(int offset,ChronoUnit unit,boolean focusDayCell,boolean withAnimation){
  if (withAnimation) {
    if (tempImageTransition == null || tempImageTransition.getStatus() == Status.STOPPED) {
      Pane monthContent=(Pane)calendarPlaceHolder.getChildren().get(0);
      this.getParent().setManaged(false);
      SnapshotParameters snapShotparams=new SnapshotParameters();
      snapShotparams.setFill(Color.TRANSPARENT);
      WritableImage temp=monthContent.snapshot(snapShotparams,new WritableImage((int)monthContent.getWidth(),(int)monthContent.getHeight()));
      ImageView tempImage=new ImageView(temp);
      calendarPlaceHolder.getChildren().add(calendarPlaceHolder.getChildren().size() - 2,tempImage);
      TranslateTransition imageTransition=new TranslateTransition(Duration.millis(160),tempImage);
      imageTransition.setToX(-offset * calendarPlaceHolder.getWidth());
      imageTransition.setOnFinished((finish) -> calendarPlaceHolder.getChildren().remove(tempImage));
      monthContent.setTranslateX(offset * calendarPlaceHolder.getWidth());
      TranslateTransition contentTransition=new TranslateTransition(Duration.millis(160),monthContent);
      contentTransition.setToX(0);
      tempImageTransition=new ParallelTransition(imageTransition,contentTransition);
      tempImageTransition.setOnFinished((finish) -> {
        calendarPlaceHolder.getChildren().remove(tempImage);
        this.getParent().setManaged(true);
      }
);
      tempImageTransition.play();
    }
  }
  YearMonth yearMonth=selectedYearMonth.get();
  DateCell dateCell=currentFocusedDayCell;
  if (dateCell == null || !(dayCellDate(dateCell).getMonth() == yearMonth.getMonth())) {
    dateCell=findDayCellOfDate(yearMonth.atDay(1));
  }
  goToDayCell(dateCell,offset,unit,focusDayCell);
}

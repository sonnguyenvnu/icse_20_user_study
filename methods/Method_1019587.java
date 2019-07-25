private PatronDatabaseEntity handle(BookPlacedOnHoldEvents placedOnHoldEvents){
  BookPlacedOnHold event=placedOnHoldEvents.getBookPlacedOnHold();
  return handle(event);
}

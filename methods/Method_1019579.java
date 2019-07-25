@Override public void handle(BookHoldCanceled event){
  sheets.update("UPDATE holds_sheet SET canceled_at = ?, status = 'CANCELED' WHERE canceled_at IS NULL AND book_id = ? AND hold_by_patron_id = ?",from(event.getWhen()),event.getBookId(),event.getPatronId());
}

private BookOnHold find(BookId id,PatronId patronId){
  return findBookOnHold.findBookOnHold(id,patronId).getOrElseThrow(() -> new IllegalArgumentException("Cannot find book on hold with Id: " + id.getBookId()));
}

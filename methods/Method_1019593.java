public Patron create(PatronType patronType,PatronId patronId,Set<Tuple2<BookId,LibraryBranchId>> patronHolds,Map<LibraryBranchId,Set<BookId>> overdueCheckouts){
  return new Patron(new PatronInformation(patronId,patronType),allCurrentPolicies(),new OverdueCheckouts(overdueCheckouts),new PatronHolds(patronHolds.stream().map(tuple -> new Hold(tuple._1,tuple._2)).collect(toSet())));
}

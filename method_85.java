private static Map<BookieSocketAddress,BookieSocketAddress> _XXXXX_(LedgerFragment ledgerFragment,Map<Integer,BookieSocketAddress> targetBookieAddresses){
  Map<BookieSocketAddress,BookieSocketAddress> bookiesMap=new HashMap<BookieSocketAddress,BookieSocketAddress>();
  for (  Integer bookieIndex : ledgerFragment.getBookiesIndexes()) {
    BookieSocketAddress oldBookie=ledgerFragment.getAddress(bookieIndex);
    BookieSocketAddress newBookie=targetBookieAddresses.get(bookieIndex);
    bookiesMap.put(oldBookie,newBookie);
  }
  return bookiesMap;
}
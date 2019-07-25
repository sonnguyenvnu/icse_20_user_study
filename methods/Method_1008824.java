private void init(int expectedSize){
  int tableSize=Hashing.closedTableSize(expectedSize,LOAD_FACTOR);
  this.hashTableKToV=createTable(tableSize);
  this.hashTableVToK=createTable(tableSize);
  this.firstInKeyInsertionOrder=null;
  this.lastInKeyInsertionOrder=null;
  this.size=0;
  this.mask=tableSize - 1;
  this.modCount=0;
}

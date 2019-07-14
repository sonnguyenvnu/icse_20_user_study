/** 
 * Set the titles (and if a second column is present) the data types for this table based on a file loaded separately. This will look for the title in column 0, and the type in column 1. Better yet, specify a column named "title" and another named "type" in the dictionary table to future-proof the code.
 * @param dictionary
 */
public void setColumnTypes(final Table dictionary){
  ensureColumn(dictionary.getRowCount() - 1);
  int titleCol=0;
  int typeCol=1;
  if (dictionary.hasColumnTitles()) {
    titleCol=dictionary.getColumnIndex("title",true);
    typeCol=dictionary.getColumnIndex("type",true);
  }
  setColumnTitles(dictionary.getStringColumn(titleCol));
  final String[] typeNames=dictionary.getStringColumn(typeCol);
  if (dictionary.getColumnCount() > 1) {
    if (getRowCount() > 1000) {
      int proc=Runtime.getRuntime().availableProcessors();
      ExecutorService pool=Executors.newFixedThreadPool(proc / 2);
      for (int i=0; i < dictionary.getRowCount(); i++) {
        final int col=i;
        pool.execute(new Runnable(){
          public void run(){
            setColumnType(col,typeNames[col]);
          }
        }
);
      }
      pool.shutdown();
      while (!pool.isTerminated()) {
        Thread.yield();
      }
    }
 else {
      for (int col=0; col < dictionary.getRowCount(); col++) {
        setColumnType(col,typeNames[col]);
      }
    }
  }
}

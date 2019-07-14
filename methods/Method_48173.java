public Stream<String> indexQuery(final String index,final IndexQuery query){
  final IndexTransaction indexTx=getIndexTransaction(index);
  return executeRead(new Callable<Stream<String>>(){
    @Override public Stream<String> call() throws Exception {
      return indexTx.queryStream(query);
    }
    @Override public String toString(){
      return "IndexQuery";
    }
  }
);
}

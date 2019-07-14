/** 
 * {@inheritDoc}
 */
@NonNull @Override public ObservableTransformer<ParseSingleGroupOp,Card> getSingleGroupTransformer(){
  return new ObservableTransformer<ParseSingleGroupOp,Card>(){
    @Override public ObservableSource<Card> apply(    Observable<ParseSingleGroupOp> upstream){
      return upstream.map(new Function<ParseSingleGroupOp,Card>(){
        @Override public Card apply(        ParseSingleGroupOp parseSingleGroupOp) throws Exception {
          return parseSingleGroup(parseSingleGroupOp.getArg1(),parseSingleGroupOp.getArg2());
        }
      }
);
    }
  }
;
}

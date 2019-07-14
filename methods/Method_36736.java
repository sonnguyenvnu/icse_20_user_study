/** 
 * {@inheritDoc}
 */
@NonNull @Override public ObservableTransformer<ParseSingleComponentOp,BaseCell> getSingleComponentTransformer(){
  return new ObservableTransformer<ParseSingleComponentOp,BaseCell>(){
    @Override public ObservableSource<BaseCell> apply(    Observable<ParseSingleComponentOp> upstream){
      return upstream.map(new Function<ParseSingleComponentOp,BaseCell>(){
        @Override public BaseCell apply(        ParseSingleComponentOp parseSingleComponentOp) throws Exception {
          return parseSingleComponent(parseSingleComponentOp.getArg1(),parseSingleComponentOp.getArg2(),parseSingleComponentOp.getArg3());
        }
      }
);
    }
  }
;
}

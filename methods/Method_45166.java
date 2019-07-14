public static Function<String,ResponseHandler> textToResource(){
  return new Function<String,ResponseHandler>(){
    @Override public ResponseHandler apply(    final String content){
      return Moco.with(Moco.text(content));
    }
  }
;
}

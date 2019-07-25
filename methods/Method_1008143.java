/** 
 * Creates a listener that listens for a response (or failure) and executes the corresponding consumer when the response (or failure) is received.
 * @param onResponse the checked consumer of the response, when the listener receives one
 * @param onFailure the consumer of the failure, when the listener receives one
 * @param < Response > the type of the response
 * @return a listener that listens for responses and invokes the consumer when received
 */
static <Response>ActionListener<Response> wrap(CheckedConsumer<Response,? extends Exception> onResponse,Consumer<Exception> onFailure){
  return new ActionListener<Response>(){
    @Override public void onResponse(    Response response){
      try {
        onResponse.accept(response);
      }
 catch (      Exception e) {
        onFailure(e);
      }
    }
    @Override public void onFailure(    Exception e){
      onFailure.accept(e);
    }
  }
;
}

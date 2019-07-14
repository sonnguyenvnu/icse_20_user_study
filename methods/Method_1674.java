private void onCancellation(FetchState fetchState){
  fetchState.getListener().onProducerFinishWithCancellation(fetchState.getId(),PRODUCER_NAME,null);
  fetchState.getConsumer().onCancellation();
}

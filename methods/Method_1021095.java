private List<BatchItemResponse> responses(final List<BatchItem> batch){
  return batch.stream().map(BatchItem::getResponse).collect(Collectors.toList());
}

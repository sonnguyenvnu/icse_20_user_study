private void retry(List<MessageQueryIndex> batch,Consumer<MessageQueryIndex> fi){
  batch.forEach(index -> retry(index,fi));
}

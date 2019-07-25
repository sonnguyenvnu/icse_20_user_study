private List<ProduceMessage> remove(){
  TransactionMessageHolder current=holder.get();
  holder.remove();
  if (current == null)   return Collections.emptyList();
  return current.get();
}

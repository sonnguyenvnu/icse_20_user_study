<T>void index(Binding<T> binding){
  bindingsMultimap.put(binding.getKey().getTypeLiteral(),binding);
}

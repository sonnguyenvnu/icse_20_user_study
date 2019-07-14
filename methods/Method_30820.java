private static RecyclerView.Adapter<?>[] appendAdapter(RecyclerView.Adapter<?>[] adapters,RecyclerView.Adapter<?> adapter){
  RecyclerView.Adapter<?>[] mergedAdapters=new RecyclerView.Adapter<?>[adapters.length + 1];
  System.arraycopy(adapters,0,mergedAdapters,0,adapters.length);
  mergedAdapters[adapters.length]=adapter;
  return mergedAdapters;
}

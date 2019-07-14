private void setUpAdapter(){
  this.adapter=new MessageThreadsAdapter(new DiffUtil.ItemCallback<Object>(){
    @Override public boolean areItemsTheSame(    final @NonNull Object oldItem,    final @NonNull Object newItem){
      return threadsAreTheSame(oldItem,newItem);
    }
    @Override public boolean areContentsTheSame(    final @NonNull Object oldItem,    final @NonNull Object newItem){
      return threadsAreTheSame(oldItem,newItem);
    }
    private boolean threadsAreTheSame(    final @NonNull Object oldItem,    final @NonNull Object newItem){
      final MessageThread oldThread=(MessageThread)oldItem;
      final MessageThread newThread=(MessageThread)newItem;
      return oldThread.id() == newThread.id();
    }
  }
);
}

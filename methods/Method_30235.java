protected ItemCollectionHolder createItemCollectionHolder(ViewGroup parent){
  ItemCollectionHolder holder=new ItemCollectionHolder(ViewUtils.inflate(R.layout.item_fragment_collection,parent));
  holder.menu=new PopupMenu(RecyclerViewUtils.getContext(holder),holder.menuButton);
  holder.menu.inflate(R.menu.item_collection_actions);
  holder.menuButton.setOnClickListener(view -> holder.menu.show());
  holder.menuButton.setOnTouchListener(holder.menu.getDragToOpenListener());
  return holder;
}

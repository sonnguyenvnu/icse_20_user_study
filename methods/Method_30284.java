@Override public void uncollect(){
  if (!mResource.hasItem()) {
    return;
  }
  Book book=mResource.getItem();
  UncollectItemManager.getInstance().write(book.getType(),book.id,getActivity());
}

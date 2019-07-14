public static ItemCollectionListResource attachTo(CollectableItem.Type itemType,long itemId,boolean followingsFirst,Fragment fragment,String tag,int requestCode){
  FragmentActivity activity=fragment.getActivity();
  ItemCollectionListResource instance=FragmentUtils.findByTag(activity,tag);
  if (instance == null) {
    instance=newInstance(itemType,itemId,followingsFirst);
    FragmentUtils.add(instance,activity,tag);
  }
  instance.setTarget(fragment,requestCode);
  return instance;
}

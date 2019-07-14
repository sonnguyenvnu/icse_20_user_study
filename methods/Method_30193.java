public static ItemAwardListResource attachTo(CollectableItem.Type itemType,long itemId,Fragment fragment,String tag,int requestCode){
  FragmentActivity activity=fragment.getActivity();
  ItemAwardListResource instance=FragmentUtils.findByTag(activity,tag);
  if (instance == null) {
    instance=newInstance(itemType,itemId);
    FragmentUtils.add(instance,activity,tag);
  }
  instance.setTarget(fragment,requestCode);
  return instance;
}

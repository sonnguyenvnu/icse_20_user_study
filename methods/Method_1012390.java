public void clear(Activity activity){
  if (factoryMap.containsKey(activity)) {
    SkinLayoutInflaterFactory skinLayoutInflaterFactory=factoryMap.remove(activity);
    skinLayoutInflaterFactory.clear();
  }
}

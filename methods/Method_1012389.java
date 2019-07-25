public void apply(AppCompatActivity activity){
  factoryMap.put(activity,new SkinLayoutInflaterFactory(activity));
  LayoutInflaterCompat.setFactory(activity.getLayoutInflater(),factoryMap.get(activity));
}

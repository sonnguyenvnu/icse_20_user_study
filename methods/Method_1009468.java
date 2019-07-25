public void prepare(@RawRes int resId){
  soundIds.put(resId,sounds.load(context,resId,1));
}

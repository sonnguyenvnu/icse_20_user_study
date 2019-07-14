public boolean getItemChecked(int position){
  if (hasCheck == false) {
    Log.e(TAG,"<<< !!! hasCheck == false  >>>>> ");
    return false;
  }
  return hashMap.get(position);
}

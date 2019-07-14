public Fragment getFragmentAtIndex(int pos){
  if (fragments.size() == 2 && pos < 2)   return fragments.get(pos);
 else   return null;
}

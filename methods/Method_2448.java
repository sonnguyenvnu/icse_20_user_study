private Token createFragment(Emit emit,String text,int lastCollectedPosition){
  return new FragmentToken(text.substring(lastCollectedPosition + 1,emit == null ? text.length() : emit.getStart()));
}

protected void setArguments(T item){
  FragmentUtils.getArgumentsBuilder(this).putParcelable(EXTRA_ITEM,item);
}

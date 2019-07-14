@Override public void bind(@NonNull DataBindingHolder holder){
  setDataBindingVariables(holder.dataBinding);
  holder.dataBinding.executePendingBindings();
}

@Override public final void bindView(final @NonNull T item,final int position,final @NonNull View view){
  final TextView tv=ButterKnife.findById(view,android.R.id.text1);
  tv.setText(getName(item));
}

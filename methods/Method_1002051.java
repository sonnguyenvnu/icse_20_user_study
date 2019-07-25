@Override public void bind(@NotNull Holder holder){
  Context ctx=holder.textView.getContext();
  holder.textView.setText(ctx.getString(text));
}

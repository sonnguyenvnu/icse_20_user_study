@Override public final T getItem(final int position){
  if (this.showNull && position == 0) {
    return null;
  }
  return this.enumConstants[position - this.nullOffset];
}

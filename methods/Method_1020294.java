public int append(final Item item){
  if (item.hasOwnerForm()) {
    throw new IllegalStateException();
  }
  items.add(item);
  item.setOwnerForm(this);
  if (layout != null) {
    AppCompatActivity a=getParentActivity();
    if (a != null) {
      View v=item.getItemView();
      a.runOnUiThread(() -> layout.addView(v));
    }
  }
  return items.size() - 1;
}

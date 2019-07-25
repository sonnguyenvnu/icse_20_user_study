public void delete(final int index){
  items.remove(index).setOwnerForm(null);
  if (layout != null) {
    AppCompatActivity a=getParentActivity();
    if (a != null) {
      a.runOnUiThread(() -> layout.removeViewAt(index));
    }
  }
}

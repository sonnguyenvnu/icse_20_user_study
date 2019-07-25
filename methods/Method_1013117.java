protected void initialize(){
  if (fields != null) {
    Iterator e=fields.iterator();
    while (e.hasNext()) {
      FieldEditor fieldEditor=(FieldEditor)e.next();
      fieldEditor.setPage(this);
      fieldEditor.setPreferenceStore(getPreferenceStore());
      fieldEditor.load();
    }
  }
}

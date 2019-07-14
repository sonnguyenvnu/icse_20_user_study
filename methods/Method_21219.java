public void takeData(final @NonNull NavigationDrawerData data){
  this.drawerData=data;
  this.sections().clear();
  this.sections().addAll(sectionsFromData(data));
  notifyDataSetChanged();
}

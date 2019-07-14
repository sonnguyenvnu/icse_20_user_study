private void addNewItem(Menu menu,int group,int order,@StringRes int text,MenuMetadata meta,@DrawableRes int icon,@DrawableRes Integer actionViewIcon){
  if (BuildConfig.DEBUG && menu.findItem(order) != null)   throw new IllegalStateException("Item already id exists: " + order);
  MenuItem item=menu.add(group,order,order,text).setIcon(icon);
  dataUtils.putDrawerMetadata(item,meta);
  if (actionViewIcon != null) {
    item.setActionView(R.layout.layout_draweractionview);
    ImageView imageView=item.getActionView().findViewById(R.id.imageButton);
    imageView.setImageResource(actionViewIcon);
    if (!mainActivity.getAppTheme().equals(AppTheme.LIGHT)) {
      imageView.setColorFilter(Color.WHITE);
    }
    item.getActionView().setOnClickListener((view) -> onNavigationItemActionClick(item));
  }
}

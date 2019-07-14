public ActionBarMenuItem addItem(int id,int icon,int backgroundColor,Drawable drawable,int width,CharSequence title){
  ActionBarMenuItem menuItem=new ActionBarMenuItem(getContext(),this,backgroundColor,isActionMode ? parentActionBar.itemsActionModeColor : parentActionBar.itemsColor);
  menuItem.setTag(id);
  if (drawable != null) {
    menuItem.iconView.setImageDrawable(drawable);
  }
 else   if (icon != 0) {
    menuItem.iconView.setImageResource(icon);
  }
  addView(menuItem,new LinearLayout.LayoutParams(width,ViewGroup.LayoutParams.MATCH_PARENT));
  menuItem.setOnClickListener(view -> {
    ActionBarMenuItem item=(ActionBarMenuItem)view;
    if (item.hasSubMenu()) {
      if (parentActionBar.actionBarMenuOnItemClick.canOpenMenu()) {
        item.toggleSubMenu();
      }
    }
 else     if (item.isSearchField()) {
      parentActionBar.onSearchFieldVisibilityChanged(item.toggleSearch(true));
    }
 else {
      onItemClick((Integer)view.getTag());
    }
  }
);
  if (title != null) {
    menuItem.setContentDescription(title);
  }
  return menuItem;
}

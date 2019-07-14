private static void tintMenuItemIcon(Menu menu,ColorStateList menuTintList,ColorStateList subMenuTintList){
  for (int i=0, size=menu.size(); i < size; ++i) {
    MenuItem menuItem=menu.getItem(i);
    Drawable icon=menuItem.getIcon();
    if (icon != null) {
      icon=tintDrawable(icon,menuTintList);
      menuItem.setIcon(icon);
    }
    SubMenu subMenu=menuItem.getSubMenu();
    if (subMenu != null) {
      tintMenuItemIcon(subMenu,subMenuTintList,subMenuTintList);
    }
  }
}

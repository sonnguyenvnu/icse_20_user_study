private void updateToolbar(String title,IIcon icon,View.OnClickListener onClickListener){
  toolbar.setTitle(title);
  toolbar.setNavigationIcon(getToolbarIcon(icon));
  toolbar.setNavigationOnClickListener(onClickListener);
}

public static void setup(MenuItem menuItem,Drawable icon,int count,Activity activity){
  View actionView=menuItem.getActionView();
  actionView.setOnClickListener(view -> activity.onMenuItemSelected(Window.FEATURE_OPTIONS_PANEL,menuItem));
  CharSequence title=menuItem.getTitle();
  if (!TextUtils.isEmpty(title)) {
    actionView.setContentDescription(title);
    TooltipUtils.setup(actionView);
  }
  ImageView iconImage=actionView.findViewById(R.id.icon);
  iconImage.setImageDrawable(icon);
  TextView badgeText=actionView.findViewById(R.id.badge);
  Context themedContext=badgeText.getContext();
  ViewCompat.setBackground(badgeText,new BadgeDrawable(themedContext));
  badgeText.setTextColor(ViewUtils.getColorFromAttrRes(R.attr.colorPrimary,0,themedContext));
  update(badgeText,count);
}

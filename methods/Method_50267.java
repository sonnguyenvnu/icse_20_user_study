/** 
 * ????
 */
@Override public void setTheme(){
  super.setTheme();
  uCropStatusColor=ThemeUtils.resolveColor(getActivity(),R.attr.gallery_ucrop_status_bar_color,R.color.gallery_default_ucrop_color_widget_active);
  uCropToolbarColor=ThemeUtils.resolveColor(getActivity(),R.attr.gallery_ucrop_toolbar_color,R.color.gallery_default_ucrop_color_widget_active);
  uCropActivityWidgetColor=ThemeUtils.resolveColor(getActivity(),R.attr.gallery_ucrop_activity_widget_color,R.color.gallery_default_ucrop_color_widget);
  uCropToolbarWidgetColor=ThemeUtils.resolveColor(getActivity(),R.attr.gallery_ucrop_toolbar_widget_color,R.color.gallery_default_toolbar_widget_color);
  uCropTitle=ThemeUtils.resolveString(getActivity(),R.attr.gallery_ucrop_toolbar_title,R.string.gallery_edit_phote);
  int pageColor=ThemeUtils.resolveColor(getContext(),R.attr.gallery_page_bg,R.color.gallery_default_page_bg);
  mRlRootView.setBackgroundColor(pageColor);
  requestStorageAccessPermissionTips=ThemeUtils.resolveString(getContext(),R.attr.gallery_request_camera_permission_tips,R.string.gallery_default_camera_access_permission_tips);
}

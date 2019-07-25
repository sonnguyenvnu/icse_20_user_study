public void Bind(){
  context.findViewById(R.id.settings_general_drawer_items).setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      new DrawerItemsDialog(new MaterialDialog.Builder(context)).show();
    }
  }
);
{
    SwitchCompat single=context.findViewById(R.id.settings_general_immersivemode);
    if (single != null) {
      single.setChecked(SettingValues.immersiveMode);
      single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        @Override public void onCheckedChanged(        CompoundButton buttonView,        boolean isChecked){
          SettingsThemeFragment.changed=true;
          SettingValues.immersiveMode=isChecked;
          SettingValues.prefs.edit().putBoolean(SettingValues.PREF_IMMERSIVE_MODE,isChecked).apply();
        }
      }
);
    }
  }
{
    SwitchCompat single=context.findViewById(R.id.settings_general_forcelanguage);
    if (single != null) {
      single.setChecked(SettingValues.overrideLanguage);
      single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        @Override public void onCheckedChanged(        CompoundButton buttonView,        boolean isChecked){
          SettingsThemeFragment.changed=true;
          SettingValues.overrideLanguage=isChecked;
          SettingValues.prefs.edit().putBoolean(SettingValues.PREF_OVERRIDE_LANGUAGE,isChecked).apply();
        }
      }
);
    }
  }
{
    SwitchCompat single=context.findViewById(R.id.settings_general_subfolder);
    if (single != null) {
      single.setChecked(SettingValues.imageSubfolders);
      single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        @Override public void onCheckedChanged(        CompoundButton buttonView,        boolean isChecked){
          SettingValues.imageSubfolders=isChecked;
          SettingValues.prefs.edit().putBoolean(SettingValues.PREF_IMAGE_SUBFOLDERS,isChecked).apply();
        }
      }
);
    }
  }
{
    if (context.findViewById(R.id.settings_general_download) != null) {
      context.findViewById(R.id.settings_general_download).setOnClickListener(new View.OnClickListener(){
        @Override public void onClick(        View v){
          new FolderChooserDialogCreate.Builder(SettingsGeneralFragment.this.context).chooseButton(R.string.btn_select).initialPath(Environment.getExternalStorageDirectory().getPath()).show();
        }
      }
);
    }
  }
  if (context.findViewById(R.id.settings_general_location) != null) {
    String loc=Reddit.appRestart.getString("imagelocation",context.getString(R.string.settings_image_location_unset));
    ((TextView)context.findViewById(R.id.settings_general_location)).setText(loc);
  }
{
    SwitchCompat single=context.findViewById(R.id.settings_general_expandedmenu);
    if (single != null) {
      single.setChecked(SettingValues.expandedToolbar);
      single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        @Override public void onCheckedChanged(        CompoundButton buttonView,        boolean isChecked){
          SettingValues.expandedToolbar=isChecked;
          SettingValues.prefs.edit().putBoolean(SettingValues.PREF_EXPANDED_TOOLBAR,isChecked).apply();
        }
      }
);
    }
  }
  if (context.findViewById(R.id.settings_general_viewtype) != null) {
    context.findViewById(R.id.settings_general_viewtype).setOnClickListener(new OnSingleClickListener(){
      @Override public void onSingleClick(      View v){
        Intent i=new Intent(context,SettingsViewType.class);
        context.startActivity(i);
      }
    }
);
  }
  if (context.findViewById(R.id.settings_general_fab_current) != null && context.findViewById(R.id.settings_general_fab) != null) {
    ((TextView)context.findViewById(R.id.settings_general_fab_current)).setText(SettingValues.fab ? (SettingValues.fabType == Constants.FAB_DISMISS ? context.getString(R.string.fab_hide) : context.getString(R.string.fab_create)) : context.getString(R.string.fab_disabled));
    context.findViewById(R.id.settings_general_fab).setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        PopupMenu popup=new PopupMenu(context,v);
        popup.getMenuInflater().inflate(R.menu.fab_settings,popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
          public boolean onMenuItemClick(          MenuItem item){
switch (item.getItemId()) {
case R.id.disabled:
              SettingValues.fab=false;
            SettingValues.prefs.edit().putBoolean(SettingValues.PREF_FAB,false).apply();
          break;
case R.id.hide:
        SettingValues.fab=true;
      SettingValues.fabType=Constants.FAB_DISMISS;
    SettingValues.prefs.edit().putInt(SettingValues.PREF_FAB_TYPE,Constants.FAB_DISMISS).apply();
  SettingValues.prefs.edit().putBoolean(SettingValues.PREF_FAB,true).apply();
break;
case R.id.create:
SettingValues.fab=true;
SettingValues.fabType=Constants.FAB_POST;
SettingValues.prefs.edit().putInt(SettingValues.PREF_FAB_TYPE,Constants.FAB_POST).apply();
SettingValues.prefs.edit().putBoolean(SettingValues.PREF_FAB,true).apply();
break;
case R.id.search:
SettingValues.fab=true;
SettingValues.fabType=Constants.FAB_SEARCH;
SettingValues.prefs.edit().putInt(SettingValues.PREF_FAB_TYPE,Constants.FAB_SEARCH).apply();
SettingValues.prefs.edit().putBoolean(SettingValues.PREF_FAB,true).apply();
break;
}
final TextView fabTitle=context.findViewById(R.id.settings_general_fab_current);
if (SettingValues.fab) {
if (SettingValues.fabType == Constants.FAB_DISMISS) {
fabTitle.setText(R.string.fab_hide);
}
 else if (SettingValues.fabType == Constants.FAB_POST) {
fabTitle.setText(R.string.fab_create);
}
 else {
fabTitle.setText(R.string.fab_search);
}
}
 else {
fabTitle.setText(R.string.fab_disabled);
}
return true;
}
}
);
popup.show();
}
}
);
}
final TextView currentMethodTitle=context.findViewById(R.id.settings_general_subreddit_search_method_current);
if (currentMethodTitle != null) {
if (SettingValues.subredditSearchMethod == Constants.SUBREDDIT_SEARCH_METHOD_DRAWER) {
currentMethodTitle.setText(context.getString(R.string.subreddit_search_method_drawer));
}
 else if (SettingValues.subredditSearchMethod == Constants.SUBREDDIT_SEARCH_METHOD_TOOLBAR) {
currentMethodTitle.setText(context.getString(R.string.subreddit_search_method_toolbar));
}
 else if (SettingValues.subredditSearchMethod == Constants.SUBREDDIT_SEARCH_METHOD_BOTH) {
currentMethodTitle.setText(context.getString(R.string.subreddit_search_method_both));
}
}
if (context.findViewById(R.id.settings_general_subreddit_search_method) != null) {
context.findViewById(R.id.settings_general_subreddit_search_method).setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
PopupMenu popup=new PopupMenu(SettingsGeneralFragment.this.context,v);
popup.getMenuInflater().inflate(R.menu.subreddit_search_settings,popup.getMenu());
popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
public boolean onMenuItemClick(MenuItem item){
switch (item.getItemId()) {
case R.id.subreddit_search_drawer:
SettingValues.subredditSearchMethod=Constants.SUBREDDIT_SEARCH_METHOD_DRAWER;
SettingValues.prefs.edit().putInt(SettingValues.PREF_SUBREDDIT_SEARCH_METHOD,Constants.SUBREDDIT_SEARCH_METHOD_DRAWER).apply();
searchChanged=true;
break;
case R.id.subreddit_search_toolbar:
SettingValues.subredditSearchMethod=Constants.SUBREDDIT_SEARCH_METHOD_TOOLBAR;
SettingValues.prefs.edit().putInt(SettingValues.PREF_SUBREDDIT_SEARCH_METHOD,Constants.SUBREDDIT_SEARCH_METHOD_TOOLBAR).apply();
searchChanged=true;
break;
case R.id.subreddit_search_both:
SettingValues.subredditSearchMethod=Constants.SUBREDDIT_SEARCH_METHOD_BOTH;
SettingValues.prefs.edit().putInt(SettingValues.PREF_SUBREDDIT_SEARCH_METHOD,Constants.SUBREDDIT_SEARCH_METHOD_BOTH).apply();
searchChanged=true;
break;
}
if (SettingValues.subredditSearchMethod == Constants.SUBREDDIT_SEARCH_METHOD_DRAWER) {
currentMethodTitle.setText(context.getString(R.string.subreddit_search_method_drawer));
}
 else if (SettingValues.subredditSearchMethod == Constants.SUBREDDIT_SEARCH_METHOD_TOOLBAR) {
currentMethodTitle.setText(context.getString(R.string.subreddit_search_method_toolbar));
}
 else if (SettingValues.subredditSearchMethod == Constants.SUBREDDIT_SEARCH_METHOD_BOTH) {
currentMethodTitle.setText(context.getString(R.string.subreddit_search_method_both));
}
return true;
}
}
);
popup.show();
}
}
);
}
final TextView currentBackButtonTitle=context.findViewById(R.id.settings_general_back_button_behavior_current);
if (SettingValues.backButtonBehavior == Constants.BackButtonBehaviorOptions.ConfirmExit.getValue()) {
currentBackButtonTitle.setText(context.getString(R.string.back_button_behavior_confirm_exit));
}
 else if (SettingValues.backButtonBehavior == Constants.BackButtonBehaviorOptions.OpenDrawer.getValue()) {
currentBackButtonTitle.setText(context.getString(R.string.back_button_behavior_drawer));
}
 else {
currentBackButtonTitle.setText(context.getString(R.string.back_button_behavior_default));
}
context.findViewById(R.id.settings_general_back_button_behavior).setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
PopupMenu popup=new PopupMenu(context,v);
popup.getMenuInflater().inflate(R.menu.back_button_behavior_settings,popup.getMenu());
popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
public boolean onMenuItemClick(MenuItem item){
switch (item.getItemId()) {
case R.id.back_button_behavior_default:
SettingValues.backButtonBehavior=Constants.BackButtonBehaviorOptions.Default.getValue();
SettingValues.prefs.edit().putInt(SettingValues.PREF_BACK_BUTTON_BEHAVIOR,Constants.BackButtonBehaviorOptions.Default.getValue()).apply();
break;
case R.id.back_button_behavior_confirm_exit:
SettingValues.backButtonBehavior=Constants.BackButtonBehaviorOptions.ConfirmExit.getValue();
SettingValues.prefs.edit().putInt(SettingValues.PREF_BACK_BUTTON_BEHAVIOR,Constants.BackButtonBehaviorOptions.ConfirmExit.getValue()).apply();
break;
case R.id.back_button_behavior_open_drawer:
SettingValues.backButtonBehavior=Constants.BackButtonBehaviorOptions.OpenDrawer.getValue();
SettingValues.prefs.edit().putInt(SettingValues.PREF_BACK_BUTTON_BEHAVIOR,Constants.BackButtonBehaviorOptions.OpenDrawer.getValue()).apply();
break;
}
if (SettingValues.backButtonBehavior == Constants.BackButtonBehaviorOptions.ConfirmExit.getValue()) {
currentBackButtonTitle.setText(context.getString(R.string.back_button_behavior_confirm_exit));
}
 else if (SettingValues.backButtonBehavior == Constants.BackButtonBehaviorOptions.OpenDrawer.getValue()) {
currentBackButtonTitle.setText(context.getString(R.string.back_button_behavior_drawer));
}
 else {
currentBackButtonTitle.setText(context.getString(R.string.back_button_behavior_default));
}
return true;
}
}
);
popup.show();
}
}
);
if (context.findViewById(R.id.settings_general_notifications_current) != null && context.findViewById(R.id.settings_general_sub_notifs_current) != null) {
if (Reddit.notificationTime > 0) {
((TextView)context.findViewById(R.id.settings_general_notifications_current)).setText(context.getString(R.string.settings_notification_short,TimeUtils.getTimeInHoursAndMins(Reddit.notificationTime,context.getBaseContext())));
setSubText();
}
 else {
((TextView)context.findViewById(R.id.settings_general_notifications_current)).setText(R.string.settings_notifdisabled);
((TextView)context.findViewById(R.id.settings_general_sub_notifs_current)).setText(R.string.settings_enable_notifs);
}
}
if (Authentication.isLoggedIn) {
if (context.findViewById(R.id.settings_general_notifications) != null) {
context.findViewById(R.id.settings_general_notifications).setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
LayoutInflater inflater=context.getLayoutInflater();
final View dialoglayout=inflater.inflate(R.layout.inboxfrequency,null);
setupNotificationSettings(dialoglayout,SettingsGeneralFragment.this.context);
}
}
);
}
if (context.findViewById(R.id.settings_general_sub_notifications) != null) {
context.findViewById(R.id.settings_general_sub_notifications).setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
showSelectDialog();
}
}
);
}
}
 else {
if (context.findViewById(R.id.settings_general_notifications) != null) {
context.findViewById(R.id.settings_general_notifications).setEnabled(false);
context.findViewById(R.id.settings_general_notifications).setAlpha(0.25f);
}
if (context.findViewById(R.id.settings_general_sub_notifications) != null) {
context.findViewById(R.id.settings_general_sub_notifications).setEnabled(false);
context.findViewById(R.id.settings_general_sub_notifications).setAlpha(0.25f);
}
}
if (context.findViewById(R.id.settings_general_sorting_current) != null) {
((TextView)context.findViewById(R.id.settings_general_sorting_current)).setText(SortingUtil.getSortingStrings()[SortingUtil.getSortingId("")]);
}
{
if (context.findViewById(R.id.settings_general_sorting) != null) {
context.findViewById(R.id.settings_general_sorting).setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
final DialogInterface.OnClickListener l2=new DialogInterface.OnClickListener(){
@Override public void onClick(DialogInterface dialogInterface,int i){
switch (i) {
case 0:
SortingUtil.defaultSorting=Sorting.HOT;
break;
case 1:
SortingUtil.defaultSorting=Sorting.NEW;
break;
case 2:
SortingUtil.defaultSorting=Sorting.RISING;
break;
case 3:
SortingUtil.defaultSorting=Sorting.TOP;
askTimePeriod();
return;
case 4:
SortingUtil.defaultSorting=Sorting.CONTROVERSIAL;
askTimePeriod();
return;
}
SettingValues.prefs.edit().putString("defaultSorting",SortingUtil.defaultSorting.name()).apply();
SettingValues.defaultSorting=SortingUtil.defaultSorting;
if (context.findViewById(R.id.settings_general_sorting_current) != null) {
((TextView)context.findViewById(R.id.settings_general_sorting_current)).setText(SortingUtil.getSortingStrings()[SortingUtil.getSortingId("")]);
}
}
}
;
AlertDialogWrapper.Builder builder=new AlertDialogWrapper.Builder(SettingsGeneralFragment.this.context);
builder.setTitle(R.string.sorting_choose);
int skip=-1;
List<String> sortingStrings=new ArrayList<>(Arrays.asList(SortingUtil.getSortingStrings()));
for (int i=0; i < sortingStrings.size(); i++) {
if (sortingStrings.get(i).equals(context.getString(R.string.sorting_best))) {
skip=i;
break;
}
}
if (skip != -1) {
sortingStrings.remove(skip);
}
builder.setSingleChoiceItems(sortingStrings.toArray(new String[0]),SortingUtil.getSortingId(""),l2);
builder.show();
}
}
);
}
}
doNotifText(context);
{
final int i2=SettingValues.defaultCommentSorting == CommentSort.CONFIDENCE ? 0 : SettingValues.defaultCommentSorting == CommentSort.TOP ? 1 : SettingValues.defaultCommentSorting == CommentSort.NEW ? 2 : SettingValues.defaultCommentSorting == CommentSort.CONTROVERSIAL ? 3 : SettingValues.defaultCommentSorting == CommentSort.OLD ? 4 : SettingValues.defaultCommentSorting == CommentSort.QA ? 5 : 0;
if (context.findViewById(R.id.settings_general_sorting_current_comment) != null) {
((TextView)context.findViewById(R.id.settings_general_sorting_current_comment)).setText(SortingUtil.getSortingCommentsStrings()[i2]);
}
if (context.findViewById(R.id.settings_general_sorting_comment) != null) {
context.findViewById(R.id.settings_general_sorting_comment).setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
final DialogInterface.OnClickListener l2=new DialogInterface.OnClickListener(){
@Override public void onClick(DialogInterface dialogInterface,int i){
CommentSort commentSorting=SettingValues.defaultCommentSorting;
switch (i) {
case 0:
commentSorting=CommentSort.CONFIDENCE;
break;
case 1:
commentSorting=CommentSort.TOP;
break;
case 2:
commentSorting=CommentSort.NEW;
break;
case 3:
commentSorting=CommentSort.CONTROVERSIAL;
break;
case 4:
commentSorting=CommentSort.OLD;
break;
case 5:
commentSorting=CommentSort.QA;
break;
}
SettingValues.prefs.edit().putString("defaultCommentSortingNew",commentSorting.name()).apply();
SettingValues.defaultCommentSorting=commentSorting;
if (context.findViewById(R.id.settings_general_sorting_current_comment) != null) {
((TextView)context.findViewById(R.id.settings_general_sorting_current_comment)).setText(SortingUtil.getSortingCommentsStrings()[i]);
}
}
}
;
AlertDialogWrapper.Builder builder=new AlertDialogWrapper.Builder(SettingsGeneralFragment.this.context);
builder.setTitle(R.string.sorting_choose);
Resources res=context.getBaseContext().getResources();
builder.setSingleChoiceItems(new String[]{res.getString(R.string.sorting_best),res.getString(R.string.sorting_top),res.getString(R.string.sorting_new),res.getString(R.string.sorting_controversial),res.getString(R.string.sorting_old),res.getString(R.string.sorting_ama)},i2,l2);
builder.show();
}
}
);
}
}
}

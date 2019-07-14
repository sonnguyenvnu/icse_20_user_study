@Override public void setupDialog(Dialog dialog,int style){
  super.setupDialog(dialog,style);
  View contentView=View.inflate(getContext(),R.layout.select_folder_bottom_sheet,null);
  final RecyclerView mRecyclerView=contentView.findViewById(R.id.folders);
  final Spinner spinner=contentView.findViewById(R.id.storage_spinner);
  currentFolderPath=contentView.findViewById(R.id.bottom_sheet_sub_title);
  exploreModePanel=contentView.findViewById(R.id.ll_explore_mode_panel);
  imgExploreMode=contentView.findViewById(R.id.toggle_hidden_icon);
  theme=ThemeHelper.getInstanceLoaded(getContext());
  sdCardPath=StorageHelper.getSdcardPath(getContext());
  mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
  mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2,Measure.pxToDp(3,getContext()),true));
  adapter=new BottomSheetAlbumsAdapter();
  mRecyclerView.setAdapter(adapter);
  spinner.setAdapter(new VolumeSpinnerAdapter(contentView.getContext()));
  spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
    @Override public void onItemSelected(    AdapterView<?> adapterView,    View view,    int pos,    long l){
switch (pos) {
case INTERNAL_STORAGE:
        displayContentFolder(Environment.getExternalStorageDirectory());
      break;
default :
    break;
}
}
@Override public void onNothingSelected(AdapterView<?> adapterView){
}
}
);
contentView.findViewById(R.id.rl_bottom_sheet_title).setBackgroundColor(theme.getPrimaryColor());
exploreModePanel.setBackgroundColor(theme.getPrimaryColor());
contentView.findViewById(R.id.ll_select_folder).setBackgroundColor(theme.getCardBackgroundColor());
theme.setColorScrollBarDrawable(ContextCompat.getDrawable(dialog.getContext(),R.drawable.ic_scrollbar));
mRecyclerView.setBackgroundColor(theme.getBackgroundColor());
fabDone=contentView.findViewById(R.id.fab_bottomsheet_done);
fabDone.setBackgroundTintList(ColorStateList.valueOf(theme.getAccentColor()));
fabDone.setImageDrawable(new IconicsDrawable(getContext()).icon(GoogleMaterial.Icon.gmd_done).color(Color.WHITE));
fabDone.setVisibility(exploreMode ? View.VISIBLE : View.GONE);
fabDone.setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
dismiss();
onFolderSelected.folderSelected(currentFolderPath.getText().toString());
}
}
);
((TextView)contentView.findViewById(R.id.bottom_sheet_title)).setText(title);
((ThemedIcon)contentView.findViewById(R.id.create_new_folder_icon)).setColor(theme.getIconColor());
contentView.findViewById(R.id.rl_create_new_folder).setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View view){
final EditText editText=new EditText(getContext());
AlertDialog insertTextDialog=AlertDialogsHelper.getInsertTextDialog(((ThemedActivity)getActivity()),editText,R.string.new_folder);
insertTextDialog.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.ok_action).toUpperCase(),new DialogInterface.OnClickListener(){
  @Override public void onClick(  DialogInterface dialogInterface,  int i){
    File folderPath=new File(currentFolderPath.getText().toString() + File.separator + editText.getText().toString());
    if (folderPath.mkdir())     displayContentFolder(folderPath);
  }
}
);
insertTextDialog.setButton(DialogInterface.BUTTON_NEGATIVE,getString(R.string.cancel).toUpperCase(),new DialogInterface.OnClickListener(){
  @Override public void onClick(  DialogInterface dialogInterface,  int i){
  }
}
);
insertTextDialog.show();
}
}
);
contentView.findViewById(R.id.rl_bottom_sheet_title).setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
if (!forzed) {
  toggleExplorerMode(!exploreMode);
  fabDone.setVisibility(exploreMode ? View.VISIBLE : View.GONE);
}
}
}
);
dialog.setContentView(contentView);
CoordinatorLayout.LayoutParams layoutParams=(CoordinatorLayout.LayoutParams)((View)contentView.getParent()).getLayoutParams();
CoordinatorLayout.Behavior behavior=layoutParams.getBehavior();
if (behavior != null && behavior instanceof BottomSheetBehavior) {
((BottomSheetBehavior)behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
}
adapter.notifyDataSetChanged();
toggleExplorerMode(exploreMode);
}

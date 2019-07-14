private void createManageInterface(Context context){
  FrameLayout frameLayout=(FrameLayout)fragmentView;
  actionBar.setTitle(LocaleController.getString("TelegramPassport",R.string.TelegramPassport));
  actionBar.createMenu().addItem(info_item,R.drawable.profile_info);
  headerCell=new HeaderCell(context);
  headerCell.setText(LocaleController.getString("PassportProvidedInformation",R.string.PassportProvidedInformation));
  headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
  linearLayout2.addView(headerCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  sectionCell=new ShadowSectionCell(context);
  sectionCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider,Theme.key_windowBackgroundGrayShadow));
  linearLayout2.addView(sectionCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  addDocumentCell=new TextSettingsCell(context);
  addDocumentCell.setBackgroundDrawable(Theme.getSelectorDrawable(true));
  addDocumentCell.setText(LocaleController.getString("PassportNoDocumentsAdd",R.string.PassportNoDocumentsAdd),true);
  linearLayout2.addView(addDocumentCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  addDocumentCell.setOnClickListener(v -> openAddDocumentAlert());
  deletePassportCell=new TextSettingsCell(context);
  deletePassportCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText3));
  deletePassportCell.setBackgroundDrawable(Theme.getSelectorDrawable(true));
  deletePassportCell.setText(LocaleController.getString("TelegramPassportDelete",R.string.TelegramPassportDelete),false);
  linearLayout2.addView(deletePassportCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  deletePassportCell.setOnClickListener(v -> {
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialog,which) -> {
      TLRPC.TL_account_deleteSecureValue req=new TLRPC.TL_account_deleteSecureValue();
      for (int a=0; a < currentForm.values.size(); a++) {
        req.types.add(currentForm.values.get(a).type);
      }
      needShowProgress();
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        for (int a=0; a < linearLayout2.getChildCount(); a++) {
          View child=linearLayout2.getChildAt(a);
          if (child instanceof TextDetailSecureCell) {
            linearLayout2.removeView(child);
            a--;
          }
        }
        needHideProgress();
        typesViews.clear();
        typesValues.clear();
        currentForm.values.clear();
        updateManageVisibility();
      }
));
    }
);
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
    builder.setMessage(LocaleController.getString("TelegramPassportDeleteAlert",R.string.TelegramPassportDeleteAlert));
    showDialog(builder.create());
  }
);
  addDocumentSectionCell=new ShadowSectionCell(context);
  addDocumentSectionCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider_bottom,Theme.key_windowBackgroundGrayShadow));
  linearLayout2.addView(addDocumentSectionCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  emptyLayout=new LinearLayout(context);
  emptyLayout.setOrientation(LinearLayout.VERTICAL);
  emptyLayout.setGravity(Gravity.CENTER);
  emptyLayout.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider_bottom,Theme.key_windowBackgroundGrayShadow));
  if (AndroidUtilities.isTablet()) {
    linearLayout2.addView(emptyLayout,new LinearLayout.LayoutParams(LayoutHelper.MATCH_PARENT,AndroidUtilities.dp(528) - ActionBar.getCurrentActionBarHeight()));
  }
 else {
    linearLayout2.addView(emptyLayout,new LinearLayout.LayoutParams(LayoutHelper.MATCH_PARENT,AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()));
  }
  emptyImageView=new ImageView(context);
  emptyImageView.setImageResource(R.drawable.no_passport);
  emptyImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_sessions_devicesImage),PorterDuff.Mode.MULTIPLY));
  emptyLayout.addView(emptyImageView,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT));
  emptyTextView1=new TextView(context);
  emptyTextView1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
  emptyTextView1.setGravity(Gravity.CENTER);
  emptyTextView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
  emptyTextView1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  emptyTextView1.setText(LocaleController.getString("PassportNoDocuments",R.string.PassportNoDocuments));
  emptyLayout.addView(emptyTextView1,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER,0,16,0,0));
  emptyTextView2=new TextView(context);
  emptyTextView2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
  emptyTextView2.setGravity(Gravity.CENTER);
  emptyTextView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
  emptyTextView2.setPadding(AndroidUtilities.dp(20),0,AndroidUtilities.dp(20),0);
  emptyTextView2.setText(LocaleController.getString("PassportNoDocumentsInfo",R.string.PassportNoDocumentsInfo));
  emptyLayout.addView(emptyTextView2,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER,0,14,0,0));
  emptyTextView3=new TextView(context);
  emptyTextView3.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
  emptyTextView3.setGravity(Gravity.CENTER);
  emptyTextView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
  emptyTextView3.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  emptyTextView3.setGravity(Gravity.CENTER);
  emptyTextView3.setText(LocaleController.getString("PassportNoDocumentsAdd",R.string.PassportNoDocumentsAdd).toUpperCase());
  emptyLayout.addView(emptyTextView3,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,30,Gravity.CENTER,0,16,0,0));
  emptyTextView3.setOnClickListener(v -> openAddDocumentAlert());
  for (int a=0, size=currentForm.values.size(); a < size; a++) {
    TLRPC.TL_secureValue value=currentForm.values.get(a);
    TLRPC.TL_secureRequiredType requiredType;
    ArrayList<TLRPC.TL_secureRequiredType> documentTypes;
    boolean documentOnly;
    if (isPersonalDocument(value.type)) {
      documentTypes=new ArrayList<>();
      requiredType=new TLRPC.TL_secureRequiredType();
      requiredType.type=value.type;
      requiredType.selfie_required=true;
      requiredType.translation_required=true;
      documentTypes.add(requiredType);
      requiredType=new TLRPC.TL_secureRequiredType();
      requiredType.type=new TLRPC.TL_secureValueTypePersonalDetails();
      documentOnly=true;
    }
 else     if (isAddressDocument(value.type)) {
      documentTypes=new ArrayList<>();
      requiredType=new TLRPC.TL_secureRequiredType();
      requiredType.type=value.type;
      requiredType.translation_required=true;
      documentTypes.add(requiredType);
      requiredType=new TLRPC.TL_secureRequiredType();
      requiredType.type=new TLRPC.TL_secureValueTypeAddress();
      documentOnly=true;
    }
 else {
      requiredType=new TLRPC.TL_secureRequiredType();
      requiredType.type=value.type;
      documentTypes=null;
      documentOnly=false;
    }
    addField(context,requiredType,documentTypes,documentOnly,a == size - 1);
  }
  updateManageVisibility();
}

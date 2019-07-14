@Override public View createView(Context context){
  actionBar.setBackButtonImage(R.drawable.ic_ab_back);
  actionBar.setAllowOverlayTitle(true);
  actionBar.setTitle(LocaleController.getString("PrivacySettings",R.string.PrivacySettings));
  actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick(){
    @Override public void onItemClick(    int id){
      if (id == -1) {
        finishFragment();
      }
    }
  }
);
  listAdapter=new ListAdapter(context);
  fragmentView=new FrameLayout(context);
  FrameLayout frameLayout=(FrameLayout)fragmentView;
  frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
  listView=new RecyclerListView(context);
  listView.setLayoutManager(layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false){
    @Override public boolean supportsPredictiveItemAnimations(){
      return false;
    }
  }
);
  listView.setVerticalScrollBarEnabled(false);
  listView.setItemAnimator(null);
  listView.setLayoutAnimation(null);
  frameLayout.addView(listView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  listView.setAdapter(listAdapter);
  listView.setOnItemClickListener((view,position) -> {
    if (!view.isEnabled()) {
      return;
    }
    if (position == blockedRow) {
      presentFragment(new PrivacyUsersActivity());
    }
 else     if (position == sessionsRow) {
      presentFragment(new SessionsActivity(0));
    }
 else     if (position == webSessionsRow) {
      presentFragment(new SessionsActivity(1));
    }
 else     if (position == clearDraftsRow) {
      AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      builder.setMessage(LocaleController.getString("AreYouSureClearDrafts",R.string.AreYouSureClearDrafts));
      builder.setPositiveButton(LocaleController.getString("Delete",R.string.Delete),(dialogInterface,i) -> {
        TLRPC.TL_messages_clearAllDrafts req=new TLRPC.TL_messages_clearAllDrafts();
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> DataQuery.getInstance(currentAccount).clearAllDrafts()));
      }
);
      builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
      showDialog(builder.create());
    }
 else     if (position == deleteAccountRow) {
      if (getParentActivity() == null) {
        return;
      }
      AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
      builder.setTitle(LocaleController.getString("DeleteAccountTitle",R.string.DeleteAccountTitle));
      builder.setItems(new CharSequence[]{LocaleController.formatPluralString("Months",1),LocaleController.formatPluralString("Months",3),LocaleController.formatPluralString("Months",6),LocaleController.formatPluralString("Years",1)},(dialog,which) -> {
        int value=0;
        if (which == 0) {
          value=30;
        }
 else         if (which == 1) {
          value=90;
        }
 else         if (which == 2) {
          value=182;
        }
 else         if (which == 3) {
          value=365;
        }
        final AlertDialog progressDialog=new AlertDialog(getParentActivity(),3);
        progressDialog.setCanCacnel(false);
        progressDialog.show();
        final TLRPC.TL_account_setAccountTTL req=new TLRPC.TL_account_setAccountTTL();
        req.ttl=new TLRPC.TL_accountDaysTTL();
        req.ttl.days=value;
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
          try {
            progressDialog.dismiss();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
          if (response instanceof TLRPC.TL_boolTrue) {
            ContactsController.getInstance(currentAccount).setDeleteAccountTTL(req.ttl.days);
            listAdapter.notifyDataSetChanged();
          }
        }
));
      }
);
      builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
      showDialog(builder.create());
    }
 else     if (position == lastSeenRow) {
      presentFragment(new PrivacyControlActivity(ContactsController.PRIVACY_RULES_TYPE_LASTSEEN));
    }
 else     if (position == phoneNumberRow) {
      presentFragment(new PrivacyControlActivity(ContactsController.PRIVACY_RULES_TYPE_PHONE));
    }
 else     if (position == groupsRow) {
      presentFragment(new PrivacyControlActivity(ContactsController.PRIVACY_RULES_TYPE_INVITE));
    }
 else     if (position == callsRow) {
      presentFragment(new PrivacyControlActivity(ContactsController.PRIVACY_RULES_TYPE_CALLS));
    }
 else     if (position == profilePhotoRow) {
      presentFragment(new PrivacyControlActivity(ContactsController.PRIVACY_RULES_TYPE_PHOTO));
    }
 else     if (position == forwardsRow) {
      presentFragment(new PrivacyControlActivity(ContactsController.PRIVACY_RULES_TYPE_FORWARDS));
    }
 else     if (position == passwordRow) {
      presentFragment(new TwoStepVerificationActivity(0));
    }
 else     if (position == passcodeRow) {
      if (SharedConfig.passcodeHash.length() > 0) {
        presentFragment(new PasscodeActivity(2));
      }
 else {
        presentFragment(new PasscodeActivity(0));
      }
    }
 else     if (position == secretWebpageRow) {
      if (MessagesController.getInstance(currentAccount).secretWebpagePreview == 1) {
        MessagesController.getInstance(currentAccount).secretWebpagePreview=0;
      }
 else {
        MessagesController.getInstance(currentAccount).secretWebpagePreview=1;
      }
      MessagesController.getGlobalMainSettings().edit().putInt("secretWebpage2",MessagesController.getInstance(currentAccount).secretWebpagePreview).commit();
      if (view instanceof TextCheckCell) {
        ((TextCheckCell)view).setChecked(MessagesController.getInstance(currentAccount).secretWebpagePreview == 1);
      }
    }
 else     if (position == contactsDeleteRow) {
      if (getParentActivity() == null) {
        return;
      }
      AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
      builder.setTitle(LocaleController.getString("Contacts",R.string.Contacts));
      builder.setMessage(LocaleController.getString("SyncContactsDeleteInfo",R.string.SyncContactsDeleteInfo));
      builder.setPositiveButton(LocaleController.getString("Cancel",R.string.Cancel),null);
      builder.setNegativeButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
        AlertDialog.Builder builder12=new AlertDialog.Builder(getParentActivity(),3);
        progressDialog=builder12.show();
        progressDialog.setCanCacnel(false);
        if (currentSync != newSync) {
          currentSync=UserConfig.getInstance(currentAccount).syncContacts=newSync;
          UserConfig.getInstance(currentAccount).saveConfig(false);
        }
        ContactsController.getInstance(currentAccount).deleteAllContacts(() -> progressDialog.dismiss());
      }
);
      showDialog(builder.create());
    }
 else     if (position == contactsSuggestRow) {
      final TextCheckCell cell=(TextCheckCell)view;
      if (newSuggest) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder.setMessage(LocaleController.getString("SuggestContactsAlert",R.string.SuggestContactsAlert));
        builder.setPositiveButton(LocaleController.getString("MuteDisable",R.string.MuteDisable),(dialogInterface,i) -> {
          TLRPC.TL_payments_clearSavedInfo req=new TLRPC.TL_payments_clearSavedInfo();
          req.credentials=clear[1];
          req.info=clear[0];
          UserConfig.getInstance(currentAccount).tmpPassword=null;
          UserConfig.getInstance(currentAccount).saveConfig(false);
          ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
            newSuggest=!newSuggest;
            cell.setChecked(newSuggest);
          }
));
        }
);
        builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
        showDialog(builder.create());
      }
 else {
        newSuggest=!newSuggest;
        cell.setChecked(newSuggest);
      }
    }
 else     if (position == contactsSyncRow) {
      newSync=!newSync;
      if (view instanceof TextCheckCell) {
        ((TextCheckCell)view).setChecked(newSync);
      }
    }
 else     if (position == secretMapRow) {
      AlertsCreator.showSecretLocationAlert(getParentActivity(),currentAccount,() -> listAdapter.notifyDataSetChanged(),false);
    }
 else     if (position == paymentsClearRow) {
      BottomSheet.Builder builder=new BottomSheet.Builder(getParentActivity());
      builder.setApplyTopPadding(false);
      builder.setApplyBottomPadding(false);
      LinearLayout linearLayout=new LinearLayout(getParentActivity());
      linearLayout.setOrientation(LinearLayout.VERTICAL);
      for (int a=0; a < 2; a++) {
        String name=null;
        if (a == 0) {
          name=LocaleController.getString("PrivacyClearShipping",R.string.PrivacyClearShipping);
        }
 else         if (a == 1) {
          name=LocaleController.getString("PrivacyClearPayment",R.string.PrivacyClearPayment);
        }
        clear[a]=true;
        CheckBoxCell checkBoxCell=new CheckBoxCell(getParentActivity(),1,21);
        checkBoxCell.setTag(a);
        checkBoxCell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        linearLayout.addView(checkBoxCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,50));
        checkBoxCell.setText(name,null,true,true);
        checkBoxCell.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        checkBoxCell.setOnClickListener(v -> {
          CheckBoxCell cell=(CheckBoxCell)v;
          int num=(Integer)cell.getTag();
          clear[num]=!clear[num];
          cell.setChecked(clear[num],true);
        }
);
      }
      BottomSheet.BottomSheetCell cell=new BottomSheet.BottomSheetCell(getParentActivity(),1);
      cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
      cell.setTextAndIcon(LocaleController.getString("ClearButton",R.string.ClearButton).toUpperCase(),0);
      cell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText));
      cell.setOnClickListener(v -> {
        try {
          if (visibleDialog != null) {
            visibleDialog.dismiss();
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        AlertDialog.Builder builder1=new AlertDialog.Builder(getParentActivity());
        builder1.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder1.setMessage(LocaleController.getString("PrivacyPaymentsClearAlert",R.string.PrivacyPaymentsClearAlert));
        builder1.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
          TLRPC.TL_payments_clearSavedInfo req=new TLRPC.TL_payments_clearSavedInfo();
          req.credentials=clear[1];
          req.info=clear[0];
          UserConfig.getInstance(currentAccount).tmpPassword=null;
          UserConfig.getInstance(currentAccount).saveConfig(false);
          ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
          }
);
        }
);
        builder1.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
        showDialog(builder1.create());
      }
);
      linearLayout.addView(cell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,50));
      builder.setCustomView(linearLayout);
      showDialog(builder.create());
    }
 else     if (position == passportRow) {
      presentFragment(new PassportActivity(PassportActivity.TYPE_PASSWORD,0,"","",null,null,null,null,null));
    }
  }
);
  return fragmentView;
}

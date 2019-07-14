@Override public View createView(Context context){
  actionBar.setBackButtonImage(R.drawable.ic_ab_back);
  if (currentType == 0) {
    actionBar.setTitle(LocaleController.getString("AutoDownloadOnMobileData",R.string.AutoDownloadOnMobileData));
  }
 else   if (currentType == 1) {
    actionBar.setTitle(LocaleController.getString("AutoDownloadOnWiFiData",R.string.AutoDownloadOnWiFiData));
  }
 else   if (currentType == 2) {
    actionBar.setTitle(LocaleController.getString("AutoDownloadOnRoamingData",R.string.AutoDownloadOnRoamingData));
  }
  if (AndroidUtilities.isTablet()) {
    actionBar.setOccupyStatusBar(false);
  }
  actionBar.setAllowOverlayTitle(true);
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
  fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
  FrameLayout frameLayout=(FrameLayout)fragmentView;
  listView=new RecyclerListView(context);
  listView.setVerticalScrollBarEnabled(false);
  ((DefaultItemAnimator)listView.getItemAnimator()).setDelayAnimations(false);
  listView.setLayoutManager(layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
  frameLayout.addView(listView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT));
  listView.setAdapter(listAdapter);
  listView.setOnItemClickListener((view,position,x,y) -> {
    if (position == autoDownloadRow) {
      if (currentPresetNum != 3) {
        if (currentPresetNum == 0) {
          typePreset.set(lowPreset);
        }
 else         if (currentPresetNum == 1) {
          typePreset.set(mediumPreset);
        }
 else         if (currentPresetNum == 2) {
          typePreset.set(highPreset);
        }
      }
      TextCheckCell cell=(TextCheckCell)view;
      boolean checked=cell.isChecked();
      if (!checked && typePreset.enabled) {
        System.arraycopy(defaultPreset.mask,0,typePreset.mask,0,4);
      }
 else {
        typePreset.enabled=!typePreset.enabled;
      }
      view.setTag(typePreset.enabled ? Theme.key_windowBackgroundChecked : Theme.key_windowBackgroundUnchecked);
      cell.setBackgroundColorAnimated(!checked,Theme.getColor(typePreset.enabled ? Theme.key_windowBackgroundChecked : Theme.key_windowBackgroundUnchecked));
      updateRows();
      if (typePreset.enabled) {
        listAdapter.notifyItemRangeInserted(autoDownloadSectionRow + 1,8);
      }
 else {
        listAdapter.notifyItemRangeRemoved(autoDownloadSectionRow + 1,8);
      }
      listAdapter.notifyItemChanged(autoDownloadSectionRow);
      SharedPreferences.Editor editor=MessagesController.getMainSettings(currentAccount).edit();
      editor.putString(key,typePreset.toString());
      editor.putInt(key2,currentPresetNum=3);
      if (currentType == 0) {
        DownloadController.getInstance(currentAccount).currentMobilePreset=currentPresetNum;
      }
 else       if (currentType == 1) {
        DownloadController.getInstance(currentAccount).currentWifiPreset=currentPresetNum;
      }
 else {
        DownloadController.getInstance(currentAccount).currentRoamingPreset=currentPresetNum;
      }
      editor.commit();
      cell.setChecked(!checked);
      DownloadController.getInstance(currentAccount).checkAutodownloadSettings();
      wereAnyChanges=true;
    }
 else     if (position == photosRow || position == videosRow || position == filesRow) {
      if (!view.isEnabled()) {
        return;
      }
      int type;
      if (position == photosRow) {
        type=DownloadController.AUTODOWNLOAD_TYPE_PHOTO;
      }
 else       if (position == videosRow) {
        type=DownloadController.AUTODOWNLOAD_TYPE_VIDEO;
      }
 else {
        type=DownloadController.AUTODOWNLOAD_TYPE_DOCUMENT;
      }
      int index=DownloadController.typeToIndex(type);
      DownloadController.Preset currentPreset;
      String key;
      String key2;
      if (currentType == 0) {
        currentPreset=DownloadController.getInstance(currentAccount).getCurrentMobilePreset();
        key="mobilePreset";
        key2="currentMobilePreset";
      }
 else       if (currentType == 1) {
        currentPreset=DownloadController.getInstance(currentAccount).getCurrentWiFiPreset();
        key="wifiPreset";
        key2="currentWifiPreset";
      }
 else {
        currentPreset=DownloadController.getInstance(currentAccount).getCurrentRoamingPreset();
        key="roamingPreset";
        key2="currentRoamingPreset";
      }
      NotificationsCheckCell cell=(NotificationsCheckCell)view;
      boolean checked=cell.isChecked();
      if (LocaleController.isRTL && x <= AndroidUtilities.dp(76) || !LocaleController.isRTL && x >= view.getMeasuredWidth() - AndroidUtilities.dp(76)) {
        if (currentPresetNum != 3) {
          if (currentPresetNum == 0) {
            typePreset.set(lowPreset);
          }
 else           if (currentPresetNum == 1) {
            typePreset.set(mediumPreset);
          }
 else           if (currentPresetNum == 2) {
            typePreset.set(highPreset);
          }
        }
        boolean hasAny=false;
        for (int a=0; a < typePreset.mask.length; a++) {
          if ((currentPreset.mask[a] & type) != 0) {
            hasAny=true;
            break;
          }
        }
        for (int a=0; a < typePreset.mask.length; a++) {
          if (checked) {
            typePreset.mask[a]&=~type;
          }
 else           if (!hasAny) {
            typePreset.mask[a]|=type;
          }
        }
        SharedPreferences.Editor editor=MessagesController.getMainSettings(currentAccount).edit();
        editor.putString(key,typePreset.toString());
        editor.putInt(key2,currentPresetNum=3);
        if (currentType == 0) {
          DownloadController.getInstance(currentAccount).currentMobilePreset=currentPresetNum;
        }
 else         if (currentType == 1) {
          DownloadController.getInstance(currentAccount).currentWifiPreset=currentPresetNum;
        }
 else {
          DownloadController.getInstance(currentAccount).currentRoamingPreset=currentPresetNum;
        }
        editor.commit();
        cell.setChecked(!checked);
        RecyclerView.ViewHolder holder=listView.findContainingViewHolder(view);
        if (holder != null) {
          listAdapter.onBindViewHolder(holder,position);
        }
        DownloadController.getInstance(currentAccount).checkAutodownloadSettings();
        wereAnyChanges=true;
        fillPresets();
      }
 else {
        if (getParentActivity() == null) {
          return;
        }
        BottomSheet.Builder builder=new BottomSheet.Builder(getParentActivity());
        builder.setApplyTopPadding(false);
        builder.setApplyBottomPadding(false);
        LinearLayout linearLayout=new LinearLayout(getParentActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        builder.setCustomView(linearLayout);
        HeaderCell headerCell=new HeaderCell(getParentActivity(),true,21,15,false);
        if (position == photosRow) {
          headerCell.setText(LocaleController.getString("AutoDownloadPhotosTitle",R.string.AutoDownloadPhotosTitle));
        }
 else         if (position == videosRow) {
          headerCell.setText(LocaleController.getString("AutoDownloadVideosTitle",R.string.AutoDownloadVideosTitle));
        }
 else {
          headerCell.setText(LocaleController.getString("AutoDownloadFilesTitle",R.string.AutoDownloadFilesTitle));
        }
        linearLayout.addView(headerCell,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
        final MaxFileSizeCell[] sizeCell=new MaxFileSizeCell[1];
        final TextCheckCell[] checkCell=new TextCheckCell[1];
        final AnimatorSet[] animatorSet=new AnimatorSet[1];
        TextCheckBoxCell[] cells=new TextCheckBoxCell[4];
        for (int a=0; a < 4; a++) {
          TextCheckBoxCell checkBoxCell=cells[a]=new TextCheckBoxCell(getParentActivity(),true);
          if (a == 0) {
            cells[a].setTextAndCheck(LocaleController.getString("AutodownloadContacts",R.string.AutodownloadContacts),(currentPreset.mask[DownloadController.PRESET_NUM_CONTACT] & type) != 0,true);
          }
 else           if (a == 1) {
            cells[a].setTextAndCheck(LocaleController.getString("AutodownloadPrivateChats",R.string.AutodownloadPrivateChats),(currentPreset.mask[DownloadController.PRESET_NUM_PM] & type) != 0,true);
          }
 else           if (a == 2) {
            cells[a].setTextAndCheck(LocaleController.getString("AutodownloadGroupChats",R.string.AutodownloadGroupChats),(currentPreset.mask[DownloadController.PRESET_NUM_GROUP] & type) != 0,true);
          }
 else           if (a == 3) {
            cells[a].setTextAndCheck(LocaleController.getString("AutodownloadChannels",R.string.AutodownloadChannels),(currentPreset.mask[DownloadController.PRESET_NUM_CHANNEL] & type) != 0,position != photosRow);
          }
          cells[a].setBackgroundDrawable(Theme.getSelectorDrawable(false));
          cells[a].setOnClickListener(v -> {
            if (!v.isEnabled()) {
              return;
            }
            checkBoxCell.setChecked(!checkBoxCell.isChecked());
            boolean hasAny=false;
            for (int b=0; b < cells.length; b++) {
              if (cells[b].isChecked()) {
                hasAny=true;
                break;
              }
            }
            if (position == videosRow && sizeCell[0].isEnabled() != hasAny) {
              ArrayList<Animator> animators=new ArrayList<>();
              sizeCell[0].setEnabled(hasAny,animators);
              if (sizeCell[0].getSize() > 2 * 1024 * 1024) {
                checkCell[0].setEnabled(hasAny,animators);
              }
              if (animatorSet[0] != null) {
                animatorSet[0].cancel();
                animatorSet[0]=null;
              }
              animatorSet[0]=new AnimatorSet();
              animatorSet[0].playTogether(animators);
              animatorSet[0].addListener(new AnimatorListenerAdapter(){
                @Override public void onAnimationEnd(                Animator animator){
                  if (animator.equals(animatorSet[0])) {
                    animatorSet[0]=null;
                  }
                }
              }
);
              animatorSet[0].setDuration(150);
              animatorSet[0].start();
            }
          }
);
          linearLayout.addView(cells[a],LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,50));
        }
        if (position != photosRow) {
          TextInfoPrivacyCell infoCell=new TextInfoPrivacyCell(getParentActivity());
          sizeCell[0]=new MaxFileSizeCell(getParentActivity()){
            @Override protected void didChangedSizeValue(            int value){
              if (position == videosRow) {
                infoCell.setText(LocaleController.formatString("AutoDownloadPreloadVideoInfo",R.string.AutoDownloadPreloadVideoInfo,AndroidUtilities.formatFileSize(value)));
                boolean enabled=value > 2 * 1024 * 1024;
                if (enabled != checkCell[0].isEnabled()) {
                  ArrayList<Animator> animators=new ArrayList<>();
                  checkCell[0].setEnabled(enabled,animators);
                  if (animatorSet[0] != null) {
                    animatorSet[0].cancel();
                    animatorSet[0]=null;
                  }
                  animatorSet[0]=new AnimatorSet();
                  animatorSet[0].playTogether(animators);
                  animatorSet[0].addListener(new AnimatorListenerAdapter(){
                    @Override public void onAnimationEnd(                    Animator animator){
                      if (animator.equals(animatorSet[0])) {
                        animatorSet[0]=null;
                      }
                    }
                  }
);
                  animatorSet[0].setDuration(150);
                  animatorSet[0].start();
                }
              }
            }
          }
;
          sizeCell[0].setSize(currentPreset.sizes[index]);
          linearLayout.addView(sizeCell[0],LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,50));
          checkCell[0]=new TextCheckCell(getParentActivity(),21,true);
          linearLayout.addView(checkCell[0],LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,48));
          checkCell[0].setOnClickListener(v -> checkCell[0].setChecked(!checkCell[0].isChecked()));
          Drawable drawable=Theme.getThemedDrawable(getParentActivity(),R.drawable.greydivider,Theme.key_windowBackgroundGrayShadow);
          CombinedDrawable combinedDrawable=new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray)),drawable);
          combinedDrawable.setFullsize(true);
          infoCell.setBackgroundDrawable(combinedDrawable);
          linearLayout.addView(infoCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
          if (position == videosRow) {
            sizeCell[0].setText(LocaleController.getString("AutoDownloadMaxVideoSize",R.string.AutoDownloadMaxVideoSize));
            checkCell[0].setTextAndCheck(LocaleController.getString("AutoDownloadPreloadVideo",R.string.AutoDownloadPreloadVideo),currentPreset.preloadVideo,false);
            infoCell.setText(LocaleController.formatString("AutoDownloadPreloadVideoInfo",R.string.AutoDownloadPreloadVideoInfo,AndroidUtilities.formatFileSize(currentPreset.sizes[index])));
          }
 else {
            sizeCell[0].setText(LocaleController.getString("AutoDownloadMaxFileSize",R.string.AutoDownloadMaxFileSize));
            checkCell[0].setTextAndCheck(LocaleController.getString("AutoDownloadPreloadMusic",R.string.AutoDownloadPreloadMusic),currentPreset.preloadMusic,false);
            infoCell.setText(LocaleController.getString("AutoDownloadPreloadMusicInfo",R.string.AutoDownloadPreloadMusicInfo));
          }
        }
 else {
          sizeCell[0]=null;
          checkCell[0]=null;
          View divider=new View(getParentActivity());
          divider.setBackgroundColor(Theme.getColor(Theme.key_divider));
          linearLayout.addView(divider,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1));
        }
        if (position == videosRow) {
          boolean hasAny=false;
          for (int b=0; b < cells.length; b++) {
            if (cells[b].isChecked()) {
              hasAny=true;
              break;
            }
          }
          if (!hasAny) {
            sizeCell[0].setEnabled(hasAny,null);
            checkCell[0].setEnabled(hasAny,null);
          }
          if (currentPreset.sizes[index] <= 2 * 1024 * 1024) {
            checkCell[0].setEnabled(false,null);
          }
        }
        FrameLayout buttonsLayout=new FrameLayout(getParentActivity());
        buttonsLayout.setPadding(AndroidUtilities.dp(8),AndroidUtilities.dp(8),AndroidUtilities.dp(8),AndroidUtilities.dp(8));
        linearLayout.addView(buttonsLayout,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,52));
        TextView textView=new TextView(getParentActivity());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
        textView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        textView.setText(LocaleController.getString("Cancel",R.string.Cancel).toUpperCase());
        textView.setPadding(AndroidUtilities.dp(10),0,AndroidUtilities.dp(10),0);
        buttonsLayout.addView(textView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,36,Gravity.TOP | Gravity.LEFT));
        textView.setOnClickListener(v14 -> builder.getDismissRunnable().run());
        textView=new TextView(getParentActivity());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
        textView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        textView.setText(LocaleController.getString("Save",R.string.Save).toUpperCase());
        textView.setPadding(AndroidUtilities.dp(10),0,AndroidUtilities.dp(10),0);
        buttonsLayout.addView(textView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,36,Gravity.TOP | Gravity.RIGHT));
        textView.setOnClickListener(v1 -> {
          if (currentPresetNum != 3) {
            if (currentPresetNum == 0) {
              typePreset.set(lowPreset);
            }
 else             if (currentPresetNum == 1) {
              typePreset.set(mediumPreset);
            }
 else             if (currentPresetNum == 2) {
              typePreset.set(highPreset);
            }
          }
          for (int a=0; a < 4; a++) {
            if (cells[a].isChecked()) {
              typePreset.mask[a]|=type;
            }
 else {
              typePreset.mask[a]&=~type;
            }
          }
          if (sizeCell[0] != null) {
            int size=(int)sizeCell[0].getSize();
            typePreset.sizes[index]=(int)sizeCell[0].getSize();
          }
          if (checkCell[0] != null) {
            if (position == videosRow) {
              typePreset.preloadVideo=checkCell[0].isChecked();
            }
 else {
              typePreset.preloadMusic=checkCell[0].isChecked();
            }
          }
          SharedPreferences.Editor editor=MessagesController.getMainSettings(currentAccount).edit();
          editor.putString(key,typePreset.toString());
          editor.putInt(key2,currentPresetNum=3);
          if (currentType == 0) {
            DownloadController.getInstance(currentAccount).currentMobilePreset=currentPresetNum;
          }
 else           if (currentType == 1) {
            DownloadController.getInstance(currentAccount).currentWifiPreset=currentPresetNum;
          }
 else {
            DownloadController.getInstance(currentAccount).currentRoamingPreset=currentPresetNum;
          }
          editor.commit();
          builder.getDismissRunnable().run();
          RecyclerView.ViewHolder holder=listView.findContainingViewHolder(view);
          if (holder != null) {
            animateChecked=true;
            listAdapter.onBindViewHolder(holder,position);
            animateChecked=false;
          }
          DownloadController.getInstance(currentAccount).checkAutodownloadSettings();
          wereAnyChanges=true;
          fillPresets();
        }
);
        showDialog(builder.create());
      }
    }
  }
);
  return fragmentView;
}

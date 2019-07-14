private void switchToCurrentSelectedMode(boolean animated){
  for (int a=0; a < mediaPages.length; a++) {
    mediaPages[a].listView.stopScroll();
  }
  int a=animated ? 1 : 0;
  RecyclerView.Adapter currentAdapter=mediaPages[a].listView.getAdapter();
  if (searching && searchWas) {
    if (animated) {
      if (mediaPages[a].selectedType == 0 || mediaPages[a].selectedType == 2) {
        searching=false;
        searchWas=false;
        switchToCurrentSelectedMode(true);
        return;
      }
 else {
        String text=searchItem.getSearchField().getText().toString();
        if (mediaPages[a].selectedType == 1) {
          if (documentsSearchAdapter != null) {
            documentsSearchAdapter.search(text);
            if (currentAdapter != documentsSearchAdapter) {
              recycleAdapter(currentAdapter);
              mediaPages[a].listView.setAdapter(documentsSearchAdapter);
            }
          }
        }
 else         if (mediaPages[a].selectedType == 3) {
          if (linksSearchAdapter != null) {
            linksSearchAdapter.search(text);
            if (currentAdapter != linksSearchAdapter) {
              recycleAdapter(currentAdapter);
              mediaPages[a].listView.setAdapter(linksSearchAdapter);
            }
          }
        }
 else         if (mediaPages[a].selectedType == 4) {
          if (audioSearchAdapter != null) {
            audioSearchAdapter.search(text);
            if (currentAdapter != audioSearchAdapter) {
              recycleAdapter(currentAdapter);
              mediaPages[a].listView.setAdapter(audioSearchAdapter);
            }
          }
        }
        if (searchItemState != 2 && mediaPages[a].emptyTextView != null) {
          mediaPages[a].emptyTextView.setText(LocaleController.getString("NoResult",R.string.NoResult));
          mediaPages[a].emptyTextView.setPadding(AndroidUtilities.dp(40),0,AndroidUtilities.dp(40),AndroidUtilities.dp(30));
          mediaPages[a].emptyTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
          mediaPages[a].emptyImageView.setVisibility(View.GONE);
        }
      }
    }
 else {
      if (mediaPages[a].listView != null) {
        if (mediaPages[a].selectedType == 1) {
          if (currentAdapter != documentsSearchAdapter) {
            recycleAdapter(currentAdapter);
            mediaPages[a].listView.setAdapter(documentsSearchAdapter);
          }
          documentsSearchAdapter.notifyDataSetChanged();
        }
 else         if (mediaPages[a].selectedType == 3) {
          if (currentAdapter != linksSearchAdapter) {
            recycleAdapter(currentAdapter);
            mediaPages[a].listView.setAdapter(linksSearchAdapter);
          }
          linksSearchAdapter.notifyDataSetChanged();
        }
 else         if (mediaPages[a].selectedType == 4) {
          if (currentAdapter != audioSearchAdapter) {
            recycleAdapter(currentAdapter);
            mediaPages[a].listView.setAdapter(audioSearchAdapter);
          }
          audioSearchAdapter.notifyDataSetChanged();
        }
      }
      if (searchItemState != 2 && mediaPages[a].emptyTextView != null) {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoResult",R.string.NoResult));
        mediaPages[a].emptyTextView.setPadding(AndroidUtilities.dp(40),0,AndroidUtilities.dp(40),AndroidUtilities.dp(30));
        mediaPages[a].emptyTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        mediaPages[a].emptyImageView.setVisibility(View.GONE);
      }
    }
  }
 else {
    mediaPages[a].emptyTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,17);
    mediaPages[a].emptyImageView.setVisibility(View.VISIBLE);
    mediaPages[a].listView.setPinnedHeaderShadowDrawable(null);
    if (mediaPages[a].selectedType == 0) {
      if (currentAdapter != photoVideoAdapter) {
        recycleAdapter(currentAdapter);
        mediaPages[a].listView.setAdapter(photoVideoAdapter);
      }
      mediaPages[a].listView.setPinnedHeaderShadowDrawable(pinnedHeaderShadowDrawable);
      mediaPages[a].emptyImageView.setImageResource(R.drawable.tip1);
      if ((int)dialog_id == 0) {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoMediaSecret",R.string.NoMediaSecret));
      }
 else {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoMedia",R.string.NoMedia));
      }
    }
 else     if (mediaPages[a].selectedType == 1) {
      if (currentAdapter != documentsAdapter) {
        recycleAdapter(currentAdapter);
        mediaPages[a].listView.setAdapter(documentsAdapter);
      }
      mediaPages[a].emptyImageView.setImageResource(R.drawable.tip2);
      if ((int)dialog_id == 0) {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoSharedFilesSecret",R.string.NoSharedFilesSecret));
      }
 else {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoSharedFiles",R.string.NoSharedFiles));
      }
    }
 else     if (mediaPages[a].selectedType == 2) {
      if (currentAdapter != voiceAdapter) {
        recycleAdapter(currentAdapter);
        mediaPages[a].listView.setAdapter(voiceAdapter);
      }
      mediaPages[a].emptyImageView.setImageResource(R.drawable.tip5);
      if ((int)dialog_id == 0) {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoSharedVoiceSecret",R.string.NoSharedVoiceSecret));
      }
 else {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoSharedVoice",R.string.NoSharedVoice));
      }
    }
 else     if (mediaPages[a].selectedType == 3) {
      if (currentAdapter != linksAdapter) {
        recycleAdapter(currentAdapter);
        mediaPages[a].listView.setAdapter(linksAdapter);
      }
      mediaPages[a].emptyImageView.setImageResource(R.drawable.tip3);
      if ((int)dialog_id == 0) {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoSharedLinksSecret",R.string.NoSharedLinksSecret));
      }
 else {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoSharedLinks",R.string.NoSharedLinks));
      }
    }
 else     if (mediaPages[a].selectedType == 4) {
      if (currentAdapter != audioAdapter) {
        recycleAdapter(currentAdapter);
        mediaPages[a].listView.setAdapter(audioAdapter);
      }
      mediaPages[a].emptyImageView.setImageResource(R.drawable.tip4);
      if ((int)dialog_id == 0) {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoSharedAudioSecret",R.string.NoSharedAudioSecret));
      }
 else {
        mediaPages[a].emptyTextView.setText(LocaleController.getString("NoSharedAudio",R.string.NoSharedAudio));
      }
    }
    mediaPages[a].emptyTextView.setPadding(AndroidUtilities.dp(40),0,AndroidUtilities.dp(40),AndroidUtilities.dp(128));
    if (mediaPages[a].selectedType == 0 || mediaPages[a].selectedType == 2) {
      if (animated) {
        searchItemState=2;
      }
 else {
        searchItemState=0;
        searchItem.setVisibility(View.INVISIBLE);
      }
    }
 else {
      if (animated) {
        if (searchItem.getVisibility() == View.INVISIBLE && !actionBar.isSearchFieldVisible()) {
          searchItemState=1;
          searchItem.setVisibility(View.VISIBLE);
          searchItem.setAlpha(0.0f);
        }
 else {
          searchItemState=0;
        }
      }
 else       if (searchItem.getVisibility() == View.INVISIBLE) {
        searchItemState=0;
        searchItem.setAlpha(1.0f);
        searchItem.setVisibility(View.VISIBLE);
      }
    }
    if (!sharedMediaData[mediaPages[a].selectedType].loading && !sharedMediaData[mediaPages[a].selectedType].endReached[0] && sharedMediaData[mediaPages[a].selectedType].messages.isEmpty()) {
      sharedMediaData[mediaPages[a].selectedType].loading=true;
      DataQuery.getInstance(currentAccount).loadMedia(dialog_id,50,0,mediaPages[a].selectedType,1,classGuid);
    }
    if (sharedMediaData[mediaPages[a].selectedType].loading && sharedMediaData[mediaPages[a].selectedType].messages.isEmpty()) {
      mediaPages[a].progressView.setVisibility(View.VISIBLE);
      mediaPages[a].listView.setEmptyView(null);
      mediaPages[a].emptyView.setVisibility(View.GONE);
    }
 else {
      mediaPages[a].progressView.setVisibility(View.GONE);
      mediaPages[a].listView.setEmptyView(mediaPages[a].emptyView);
    }
    mediaPages[a].listView.setVisibility(View.VISIBLE);
  }
  if (searchItemState == 2 && actionBar.isSearchFieldVisible()) {
    ignoreSearchCollapse=true;
    actionBar.closeSearchField();
  }
  if (actionBar.getTranslationY() != 0) {
    mediaPages[a].layoutManager.scrollToPositionWithOffset(0,(int)actionBar.getTranslationY());
  }
}

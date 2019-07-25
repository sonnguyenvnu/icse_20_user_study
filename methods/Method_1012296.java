private void state(CustomStateOptions options){
  if (!TextUtils.isEmpty(options.getMessage())) {
    stMessage.setVisibility(VISIBLE);
    stMessage.setText(options.getMessage());
  }
 else {
    stMessage.setVisibility(GONE);
  }
  if (options.isLoading()) {
    stProgress.setVisibility(VISIBLE);
    stImage.setVisibility(GONE);
    stButton.setVisibility(GONE);
  }
 else {
    stProgress.setVisibility(GONE);
    if (options.getImageRes() != 0) {
      stImage.setVisibility(VISIBLE);
      stImage.setImageResource(options.getImageRes());
    }
 else {
      stImage.setVisibility(GONE);
    }
    if (options.getClickListener() != null) {
      stButton.setVisibility(VISIBLE);
      stButton.setOnClickListener(options.getClickListener());
      if (!TextUtils.isEmpty(options.getButtonText())) {
        stButton.setText(options.getButtonText());
      }
    }
 else {
      stButton.setVisibility(GONE);
    }
  }
}

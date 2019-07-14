private void fixLayout(){
  if (avatarContainer != null) {
    avatarContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
      @Override public boolean onPreDraw(){
        if (avatarContainer != null) {
          avatarContainer.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        return fixLayoutInternal();
      }
    }
);
  }
}

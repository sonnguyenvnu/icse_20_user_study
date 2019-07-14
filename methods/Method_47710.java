@Override public void onModelChange(){
  new Handler(Looper.getMainLooper()).post(() -> {
    toolbar.setTitle(habit.getName());
  }
);
  controller.onToolbarChanged();
}

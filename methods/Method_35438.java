private String getTitle(){
  return getContext().getString(isEditMode() ? R.string.mp_play_list_edit : R.string.mp_play_list_create);
}

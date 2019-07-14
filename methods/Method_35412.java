@OnCheckedChanged({R.id.radio_button_play_list,R.id.radio_button_music,R.id.radio_button_local_files,R.id.radio_button_settings}) public void onRadioButtonChecked(RadioButton button,boolean isChecked){
  if (isChecked) {
    onItemChecked(radioButtons.indexOf(button));
  }
}

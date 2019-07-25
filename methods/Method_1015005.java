void collapse(){
  extension.reset();
  emotionImageView.setImageResource(R.mipmap.ic_cheat_emo);
  rootLinearLayout.hideAttachedInput(true);
  rootLinearLayout.hideCurrentInput(editText);
}

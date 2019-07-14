private Button createFolderButton(String text){
  Button button;
  if (lastUsedFolderButton >= folderButtons.size()) {
    button=new Button(mainActivity);
    button.setTextColor(Utils.getColor(mainActivity,android.R.color.white));
    button.setTextSize(13);
    button.setLayoutParams(buttonParams);
    button.setBackgroundResource(0);
    folderButtons.add(button);
  }
 else {
    button=folderButtons.get(lastUsedFolderButton);
  }
  button.setText(text);
  lastUsedFolderButton++;
  return button;
}

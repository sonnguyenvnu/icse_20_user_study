private void setupNameController(){
  namePanel.setController(new NameDescriptionPanel.Controller(){
    @Override public void onColorPickerClicked(    int previousColor){
      ColorPickerDialog picker=colorPickerDialogFactory.create(previousColor);
      picker.setListener(c -> {
        prefs.setDefaultHabitColor(c);
        namePanel.setColor(c);
      }
);
      picker.show(getFragmentManager(),"picker");
    }
  }
);
}

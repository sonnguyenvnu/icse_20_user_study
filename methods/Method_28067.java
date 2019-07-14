@Override public void onColorSelected(@NonNull String color){
  description.getEditText().setText(color.replaceFirst("#",""));
}

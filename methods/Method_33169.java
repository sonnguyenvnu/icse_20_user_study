private void initialize(){
  this.getStyleClass().add(DEFAULT_STYLE_CLASS);
  if ("dalvik".equals(System.getProperty("java.vm.name").toLowerCase())) {
    this.setStyle("-fx-skin: \"com.jfoenix.android.skins.JFXTextFieldSkinAndroid\";");
  }
}

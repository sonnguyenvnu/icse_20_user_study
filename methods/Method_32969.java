/** 
 * init fxml when loaded.
 */
@PostConstruct public void init(){
  validatedText.focusedProperty().addListener((o,oldVal,newVal) -> {
    if (!newVal) {
      validatedText.validate();
    }
  }
);
  validatedPassword.focusedProperty().addListener((o,oldVal,newVal) -> {
    if (!newVal) {
      validatedPassword.validate();
    }
  }
);
  jfxTextArea.focusedProperty().addListener((o,oldVal,newVal) -> {
    if (!newVal) {
      jfxTextArea.validate();
    }
  }
);
}

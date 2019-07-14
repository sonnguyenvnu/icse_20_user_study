protected JEditTextArea createTextArea(){
  return new JEditTextArea(new PdeTextAreaDefaults(mode),new PdeInputHandler(this));
}

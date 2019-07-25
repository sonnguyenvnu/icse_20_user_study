public void run(Keyboard keyboard) throws NoInputException {
  if (screen != null) {
    throw new IllegalStateException();
  }
  screen=new Screen();
  skb=new SmartKeyboard(keyboard);
  init();
}

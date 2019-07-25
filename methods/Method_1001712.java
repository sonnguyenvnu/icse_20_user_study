public String input(Screen screen) throws NoInputException {
  final String s=keyboard.input();
  history.add(s);
  screen.print("<i>? " + s);
  return s;
}

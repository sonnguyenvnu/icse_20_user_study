static public void registerFormatGuesser(String format,FormatGuesser guesser){
  List<FormatGuesser> guessers=formatToGuessers.get(format);
  if (guessers == null) {
    guessers=new LinkedList<FormatGuesser>();
    formatToGuessers.put(format,guessers);
  }
  guessers.add(0,guesser);
}

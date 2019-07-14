public static String toLunoPair(CurrencyPair pair){
  return toLunoCurrency(pair.base) + toLunoCurrency(pair.counter);
}

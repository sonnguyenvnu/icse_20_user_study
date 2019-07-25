static List<Character> illion(final int i){
  return stringShow.show(stream("thousand","million","billion","trillion","quadrillion","quintillion","sextillion","septillion","octillion","nonillion","decillion","undecillion","duodecillion","tredecillion","quattuordecillion","quindecillion","sexdecillion","septendecillion","octodecillion","novemdecillion","vigintillion").append(Stream.repeat("<unsupported ?illion>")).index(i)).toList();
}

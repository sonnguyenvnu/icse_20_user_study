static List<Character> show(final List<Character> cs){
  if (cs.isEmpty())   return List.nil();
 else {
    final char d1=cs.head();
    final List<Character> d1r=cs.tail();
    if (d1r.isEmpty())     return show(d1);
 else {
      final char d2=d1r.head();
      final List<Character> d2r=d1r.tail();
      return d2r.isEmpty() ? d1 == '0' ? show(d2) : d1 == '1' ? stringShow.showl(list("ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen").index(d2 - '0')) : stringShow.showl(list("twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety").index(d1 - '0' - 2)).append(d2 == '0' ? List.nil() : show(d2).cons('-')) : d1 == '0' && d2 == '0' && d2r.head() == '0' ? List.nil() : d1 == '0' ? show(list(d2,d2r.head())) : d2 == '0' && d2r.head() == '0' ? show(d1).append(stringShow.showl(" hundred")) : show(d1).append(stringShow.showl(" hundred and ")).append(show(list(d2,d2r.head())));
    }
  }
}

/** 
 * authshort format: added by Kolja Brix, kbx@users.sourceforge.net given author names Isaac Newton and James Maxwell and Albert Einstein and N. Bohr Isaac Newton and James Maxwell and Albert Einstein Isaac Newton and James Maxwell Isaac Newton yield NME+ NME NM Newton
 */
public static String authshort(String authorField){
  String fixedAuthorField=AuthorList.fixAuthorForAlphabetization(authorField);
  StringBuilder author=new StringBuilder();
  String[] tokens=fixedAuthorField.split("\\band\\b");
  int i=0;
  if (tokens.length == 1) {
    author.append(authNofMth(fixedAuthorField,fixedAuthorField.length(),1));
  }
 else   if (tokens.length >= 2) {
    while ((tokens.length > i) && (i < 3)) {
      author.append(authNofMth(fixedAuthorField,1,i + 1));
      i++;
    }
    if (tokens.length > 3) {
      author.append('+');
    }
  }
  return author.toString();
}

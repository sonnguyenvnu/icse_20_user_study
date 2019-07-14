/** 
 * Removes all mnemonics, then sets a mnemonic for each menu and menu item recursively by these rules: <ol> <li> It tries to assign one of <a href="http://techbase.kde.org/Projects/Usability/HIG/Keyboard_Accelerators"> KDE's defaults</a>.</li> <li> Failing that, it loops through the first letter of each word, where a word is a block of Unicode "alphabetical" chars, looking for an upper-case ASCII mnemonic that is not taken. This is to try to be relevant, by using a letter well-associated with the command. (MS guidelines) </li> <li> Ditto, but with lowercase. </li> <li> Next, it tries the second ASCII character, if its width &gt;= half the width of 'A'. </li> <li> If the first letters are all taken/non-ASCII, then it loops through the ASCII letters in the item, widest to narrowest, seeing if any of them is not taken. To improve readability, it discriminates against decenders (qypgj), imagining they have 2/3 their actual width. (MS guidelines: avoid decenders). It also discriminates against vowels, imagining they have 2/3 their actual width. (MS and Gnome guidelines: avoid vowels.) </li> <li>Failing that, it will loop left-to-right for an available digit. This is a last resort because the normal setMnemonic dislikes them.</li> <li> If that doesn't work, it doesn't assign a mnemonic. </li> </ol> As a special case, strings starting "sketchbook \u2192 " have that bit ignored because otherwise the Recent menu looks awful. However, the name <tt>"sketchbook \u2192 Sketch"</tt>, for example, will have the 'S' of "Sketch" chosen, but the 's' of 'sketchbook will get underlined. No letter by an underscore will be assigned. Disabled on Mac, per Apple guidelines. <tt>menu</tt> may contain nulls. Author: George Bateman. Initial work Myer Nore.
 * @param menu A menu, a list of menus or an array of menu items to set mnemonics for.
 */
static public void setMenuMnemonics(JMenuItem... menu){
  if (Platform.isMacOS())   return;
  if (menu.length == 0)   return;
  final String[] kdePreDefStrs={"&file","&new","&open","open&recent","&save","save&as","saveacop&y","saveas&template","savea&ll","reloa&d","&print","printpre&view","&import","e&xport","&closefile","clos&eallfiles","&quit","&edit","&undo","re&do","cu&t","&copy","&paste","&delete","select&all","dese&lect","&find","find&next","findpre&vious","&replace","&gotoline","&view","&newview","close&allviews","&splitview","&removeview","splitter&orientation","&horizontal","&vertical","view&mode","&fullscreenmode","&zoom","zoom&in","zoom&out","zoomtopage&width","zoomwhole&page","zoom&factor","&insert","&format","&go","&up","&back","&forward","&home","&go","&previouspage","&nextpage","&firstpage","&lastpage","read&updocument","read&downdocument","&back","&forward","&gotopage","&bookmarks","&addbookmark","bookmark&tabsasfolder","&editbookmarks","&newbookmarksfolder","&tools","&settings","&toolbars","configure&shortcuts","configuretool&bars","&configure.*","&help",".+&handbook","&whatsthis","report&bug","&aboutprocessing","about&kde","&beenden","&suchen","&preferncias","&sair","&rechercher"};
  Pattern[] kdePreDefPats=new Pattern[kdePreDefStrs.length];
  for (int i=0; i < kdePreDefStrs.length; i++) {
    kdePreDefPats[i]=Pattern.compile(kdePreDefStrs[i].replace("&",""));
  }
  final Pattern nonAAlpha=Pattern.compile("[^A-Za-z]");
  FontMetrics fmTmp=null;
  for (  JMenuItem m : menu) {
    if (m != null) {
      fmTmp=m.getFontMetrics(m.getFont());
      break;
    }
  }
  if (fmTmp == null)   return;
  final FontMetrics fm=fmTmp;
  final Comparator<Character> charComparator=new Comparator<Character>(){
    public int compare(    Character ch1,    Character ch2){
      float w1=fm.charWidth(ch1), w2=fm.charWidth(ch2);
      for (      char bad : baddies) {
        if (bad == ch1)         w1*=0.66f;
        if (bad == ch2)         w2*=0.66f;
      }
      return (int)Math.signum(w2 - w1);
    }
  }
;
  final List<Character> taken=new ArrayList<>(menu.length);
  char firstChar;
  char[] cleanChars;
  Character[] cleanCharas;
  for (  JMenuItem jmi : menu) {
    if (jmi == null)     continue;
    if (jmi.getText() == null)     continue;
    jmi.setMnemonic(0);
    String asciiName=nonAAlpha.matcher(jmi.getText()).replaceAll("");
    String lAsciiName=asciiName.toLowerCase();
    for (int i=0; i < kdePreDefStrs.length; i++) {
      if (kdePreDefPats[i].matcher(lAsciiName).matches()) {
        char mnem=asciiName.charAt(kdePreDefStrs[i].indexOf("&"));
        jmi.setMnemonic(mnem);
        jmi.setDisplayedMnemonicIndex(jmi.getText().indexOf(mnem));
        taken.add((char)(mnem | 32));
        break;
      }
    }
  }
  algorithmicAssignment:   for (  JMenuItem jmi : menu) {
    if (jmi == null)     continue;
    if (jmi.getText() == null)     continue;
    if (jmi.getMnemonic() != 0)     continue;
    String cleanString=jmi.getText();
    if (cleanString.startsWith("sketchbook \u2192 "))     cleanString=cleanString.substring(13);
    if (cleanString.length() == 0)     continue;
    final List<Character> banned=new ArrayList<>();
    for (int i=0; i < cleanString.length(); i++) {
      if (cleanString.charAt(i) == '_') {
        if (i > 0)         banned.add(Character.toLowerCase(cleanString.charAt(i - 1)));
        if (i + 1 < cleanString.length())         banned.add(Character.toLowerCase(cleanString.charAt(i + 1)));
      }
    }
    for (    String wd : cleanString.split("[^\\p{IsAlphabetic}]")) {
      if (wd.length() == 0)       continue;
      firstChar=wd.charAt(0);
      if (taken.contains(Character.toLowerCase(firstChar)))       continue;
      if (banned.contains(Character.toLowerCase(firstChar)))       continue;
      if ('A' <= firstChar && firstChar <= 'Z') {
        jmi.setMnemonic(firstChar);
        jmi.setDisplayedMnemonicIndex(jmi.getText().indexOf(firstChar));
        taken.add((char)(firstChar | 32));
        continue algorithmicAssignment;
      }
    }
    for (    String wd : cleanString.split("[^\\p{IsAlphabetic}]")) {
      if (wd.length() == 0)       continue;
      firstChar=wd.charAt(0);
      if (taken.contains(Character.toLowerCase(firstChar)))       continue;
      if (banned.contains(Character.toLowerCase(firstChar)))       continue;
      if ('a' <= firstChar && firstChar <= 'z') {
        jmi.setMnemonic(firstChar);
        jmi.setDisplayedMnemonicIndex(jmi.getText().indexOf(firstChar));
        taken.add(firstChar);
        continue algorithmicAssignment;
      }
    }
    cleanString=nonAAlpha.matcher(jmi.getText()).replaceAll("");
    if (cleanString.length() >= 2) {
      char ascii2nd=cleanString.charAt(1);
      if (!taken.contains((char)(ascii2nd | 32)) && !banned.contains((char)(ascii2nd | 32)) && fm.charWidth('A') <= 2 * fm.charWidth(ascii2nd)) {
        jmi.setMnemonic(ascii2nd);
        jmi.setDisplayedMnemonicIndex(jmi.getText().indexOf(ascii2nd));
        taken.add((char)(ascii2nd | 32));
        continue algorithmicAssignment;
      }
    }
    cleanChars=cleanString.toCharArray();
    cleanCharas=new Character[cleanChars.length];
    for (int i=0; i < cleanChars.length; i++) {
      cleanCharas[i]=cleanChars[i];
    }
    Arrays.sort(cleanCharas,charComparator);
    for (    char mnem : cleanCharas) {
      if (taken.contains(Character.toLowerCase(mnem)))       continue;
      if (banned.contains(Character.toLowerCase(mnem)))       continue;
      jmi.setMnemonic(mnem);
      jmi.setDisplayedMnemonicIndex(jmi.getText().indexOf(mnem));
      taken.add(Character.toLowerCase(mnem));
      continue algorithmicAssignment;
    }
    for (    char digit : jmi.getText().replaceAll("[^0-9]","").toCharArray()) {
      if (taken.contains(digit))       continue;
      if (banned.contains(digit))       continue;
      jmi.setMnemonic(KeyEvent.VK_0 + digit - '0');
      taken.add(digit);
      continue algorithmicAssignment;
    }
  }
  for (  JMenuItem jmi : menu) {
    if (jmi instanceof JMenu)     setMenuMnemsInside((JMenu)jmi);
  }
}

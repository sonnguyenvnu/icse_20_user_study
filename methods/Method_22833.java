/** 
 * Add a keyword, and the associated coloring. KEYWORD2 and KEYWORD3 should only be used with functions (where parens are present). This is done for the extra paren handling.
 * @param coloring one of KEYWORD1, KEYWORD2, LITERAL1, etc.
 */
public void addColoring(String keyword,String coloring){
  if (keywordColoring == null) {
    keywordColoring=new KeywordMap(false);
  }
  int num=coloring.charAt(coloring.length() - 1) - '1';
  int id=0;
switch (coloring.charAt(0)) {
case 'K':
    id=Token.KEYWORD1 + num;
  keywordColoring.add(keyword,(byte)id,false);
if (id == Token.KEYWORD6) {
  keywordColoring.add(keyword,(byte)id,true);
}
break;
case 'L':
id=Token.LITERAL1 + num;
keywordColoring.add(keyword,(byte)id,false);
break;
case 'F':
id=Token.FUNCTION1 + num;
keywordColoring.add(keyword,(byte)id,true);
break;
}
}

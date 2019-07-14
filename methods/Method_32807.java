/** 
 * Applies the 'controlCharsAliases' mapping to all the strings in *buttons Modifies the array, doesn't return a new one.
 */
void replaceAliases(String[][] buttons){
  for (int i=0; i < buttons.length; i++)   for (int j=0; j < buttons[i].length; j++)   buttons[i][j]=controlCharsAliases.get(buttons[i][j],buttons[i][j]);
}

/** 
 * Add the latest expression to the preference store
 * @param s
 */
public void addLatestExpression(String s){
synchronized (this) {
    ((TopList)_preferenceStore.get("scripting.expressions")).add(s);
  }
}

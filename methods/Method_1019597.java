/** 
 * {@inheritDoc}
 */
@Override public float paint(Token token,Graphics2D g,float x,float y,RSyntaxTextArea host,TabExpander e,float clipStart){
  return paintImpl(token,g,x,y,host,e,clipStart,false,false);
}

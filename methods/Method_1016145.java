/** 
 * Initializes checkbox settings.
 * @param text initial text
 * @param icon initial icon
 */
@Override protected void init(final String text,final Icon icon){
  model=new TristateCheckBoxModel();
  setModel(model);
  super.init(text,icon);
}

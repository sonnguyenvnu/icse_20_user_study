/** 
 * {@inheritDoc}
 */
@Override protected void init(final String text,final Icon icon){
  super.init(text,icon);
  addMouseMotionListener(this);
  addMouseListener(this);
  addActionListener(this);
}

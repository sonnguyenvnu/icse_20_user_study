/** 
 * This method is implemented when the ItemReader requires any open time processing.
 * @param checkpoint
 * @throws Exception
 */
@Override public void open(Serializable checkpoint) throws Exception {
  final List<Object> list=new ArrayList<Object>();
  for (int i=0; i < random.nextInt(50); i++) {
    list.add(new Object());
  }
  items=list.iterator();
}

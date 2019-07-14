/** 
 * Populates the JMenu with JMenuItems, one for each Tool that has a reference accompanying it. The JMenuItems open the index.htm/index.html file of the reference in the user's default browser, or the readme.txt in the user's default text editor.
 * @param toolsList A list of Tools to be added
 * @param subMenu The JMenu to which the JMenuItems corresponding to the Tools are to be added
 * @return true if and only if any JMenuItems were added; false otherwise
 */
private boolean addToolReferencesToSubMenu(List<ToolContribution> toolsList,JMenu subMenu){
  boolean isItemAdded=false;
  Iterator<ToolContribution> iter=toolsList.iterator();
  while (iter.hasNext()) {
    final ToolContribution toolContrib=iter.next();
    final File toolRef=new File(toolContrib.getFolder(),"reference/index.html");
    if (toolRef.exists()) {
      JMenuItem libRefItem=new JMenuItem(toolContrib.getName());
      libRefItem.addActionListener(new ActionListener(){
        @Override public void actionPerformed(        ActionEvent arg0){
          showReferenceFile(toolRef);
        }
      }
);
      subMenu.add(libRefItem);
      isItemAdded=true;
    }
  }
  return isItemAdded;
}

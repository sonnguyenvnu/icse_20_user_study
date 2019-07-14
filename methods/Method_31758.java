private void addBottomArea(Container container){
  JPanel panel=new JPanel();
  panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
  ItemListener listener=new ItemListener(){
    public void itemStateChanged(    ItemEvent e){
      updateResults();
    }
  }
;
  iFieldSets=new FieldSet[]{new FieldSet("Month Based",new FieldGroup[]{new FieldGroup(listener,"Years",YEARS),new FieldGroup(listener,"Months",MONTHS),new FieldGroup(listener,"Days",DAYS),new FieldGroup(listener,"Hours",HOURS),new FieldGroup(listener,"Minutes",MINUTES),new FieldGroup(listener,"Seconds",SECONDS)}),new FieldSet("Week Based",new FieldGroup[]{new FieldGroup(listener,"Weekyears",WEEKYEARS),new FieldGroup(listener,"Weeks",WEEKS),new FieldGroup(listener,"Days",DAYS),new FieldGroup(listener,"Hours",HOURS),new FieldGroup(listener,"Minutes",MINUTES),new FieldGroup(listener,"Seconds",SECONDS)})};
  for (int i=0; i < iFieldSets.length; i++) {
    if (i > 0) {
      panel.add(Box.createHorizontalStrut(10));
    }
    iFieldSets[i].addTo(panel);
  }
  panel.add(Box.createVerticalGlue());
  container.add(fixedHeight(panel));
}

@Override public void initialize(URL location,ResourceBundle resources){
  stringControllers=new ArrayList<>();
  stringControllersCount=new SimpleIntegerProperty(0);
  fileControllers=new ArrayList<>();
  fileControllersCount=new SimpleIntegerProperty(0);
  addFileField();
  addStringField();
}

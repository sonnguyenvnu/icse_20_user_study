@Override public void errorTableDoubleClick(Object item){
  JavaProblem p=(JavaProblem)item;
  String[] suggs=p.getImportSuggestions();
  if (suggs != null && suggs.length > 0) {
    String[] list=p.getImportSuggestions();
    String className=list[0].substring(list[0].lastIndexOf('.') + 1);
    String[] temp=new String[list.length];
    for (int i=0; i < list.length; i++) {
      temp[i]="<html>Import '" + className + "' <font color=#777777>(" + list[i] + ")</font></html>";
    }
    Point mouse=MouseInfo.getPointerInfo().getLocation();
    showImportSuggestion(temp,mouse.x,mouse.y);
  }
 else {
    errorTableClick(item);
  }
}

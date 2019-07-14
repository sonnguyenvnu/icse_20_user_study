private void initExceptionSorter(){
  if (exceptionSorter instanceof NullExceptionSorter) {
    if (driver instanceof MockDriver) {
      return;
    }
  }
 else   if (this.exceptionSorter != null) {
    return;
  }
  this.exceptionSorter=new MySqlExceptionSorter();
  this.isMySql=true;
}

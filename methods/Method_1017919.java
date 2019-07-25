public void build(){
  updateServiceState();
  this.checkDate=new Date();
  this.alertsWithoutComponent=getAlertsWithoutComponent(this.alerts);
}

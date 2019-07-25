public void start(){
  this.subscription=this.getEventStore().findAll().concatWith(this.getEventStore()).concatMap(this::updateSnapshot).subscribe();
}

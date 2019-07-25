public final void create(@NonNull Context context,@NonNull ApplicationExtension extension){
  this.context=context;
  this.extension=extension;
  onApplicationCreated(context);
}

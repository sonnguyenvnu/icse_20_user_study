public static Builder create(ComponentContext context,int defStyleAttr,int defStyleRes,String simpleName){
  final Builder builder=new Builder();
  builder.init(context,defStyleAttr,defStyleRes,new Row(simpleName));
  return builder;
}

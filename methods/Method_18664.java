public static Builder create(ComponentContext context,int defStyleAttr,int defStyleRes){
  final Builder builder=new Builder();
  builder.init(context,defStyleAttr,defStyleRes,new Wrapper());
  return builder;
}

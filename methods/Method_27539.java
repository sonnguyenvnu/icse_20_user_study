@NonNull @Override protected MaterialAboutList getMaterialAboutList(@NonNull Context context){
  MaterialAboutCard.Builder appCardBuilder=new MaterialAboutCard.Builder();
  buildApp(context,appCardBuilder);
  MaterialAboutCard.Builder miscCardBuilder=new MaterialAboutCard.Builder();
  buildMisc(context,miscCardBuilder);
  MaterialAboutCard.Builder authorCardBuilder=new MaterialAboutCard.Builder();
  buildAuthor(context,authorCardBuilder);
  MaterialAboutCard.Builder newLogoAuthor=new MaterialAboutCard.Builder();
  MaterialAboutCard.Builder logoAuthor=new MaterialAboutCard.Builder();
  buildLogo(context,newLogoAuthor,logoAuthor);
  return new MaterialAboutList(appCardBuilder.build(),miscCardBuilder.build(),authorCardBuilder.build(),newLogoAuthor.build(),logoAuthor.build());
}

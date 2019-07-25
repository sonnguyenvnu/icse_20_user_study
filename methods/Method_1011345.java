@CheckingMethod public static void check(final TypeCheckingContext typeCheckingContext,SNode node,String url){
  if (!((url.startsWith("http://") || url.startsWith("https://")))) {
{
      MessageTarget errorTarget=new NodeMessageTarget();
      IErrorReporter _reporter_2309309498=typeCheckingContext.reportTypeError(node,"Help URL should start with http:// or https://","r:cafe8450-2876-42f2-9c43-75da10155c47(jetbrains.mps.lang.resources.typesystem)","5177162104569565281",null,errorTarget);
    }
  }
 else {
    try {
      new URL(url);
    }
 catch (    MalformedURLException e) {
{
        MessageTarget errorTarget=new NodeMessageTarget();
        IErrorReporter _reporter_2309309498=typeCheckingContext.reportTypeError(node,"Malformed URL: " + e.getMessage(),"r:cafe8450-2876-42f2-9c43-75da10155c47(jetbrains.mps.lang.resources.typesystem)","5177162104569127649",null,errorTarget);
      }
    }
  }
}

private boolean check(){
  return new ModelAccessHelper(myProject.getModelAccess()).runReadAction(new Computable<Boolean>(){
    public Boolean compute(){
      return checkImpl();
    }
  }
);
}

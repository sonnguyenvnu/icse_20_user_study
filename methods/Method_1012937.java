@Override public ActionResult execute(){
  NationalitiesData nationalities=new NationalitiesData(NationalityHelper.getNationalities());
  return new JsonResult(nationalities);
}

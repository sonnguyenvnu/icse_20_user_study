/** 
 * Display search window and search given search
 * @param param Search parameter
 */
public static void open(Parameter param){
  FxSearch fx=new FxSearch();
  fx.opacityProperty();
  SearchObj obj=new SearchObj(fx){
    @Override public String title(){
      return "dummy";
    }
    @Override public void run(){
      update(Search.search(param));
    }
  }
;
  obj.run();
  fx.show();
}

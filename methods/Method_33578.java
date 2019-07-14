/** 
 * ????????
 */
private void transformData(final MovieDetailBean movieDetailBean){
  new Thread(new Runnable(){
    @Override public void run(){
      for (int i=0; i < movieDetailBean.getDirectors().size(); i++) {
        movieDetailBean.getDirectors().get(i).setType("??");
      }
      for (int i=0; i < movieDetailBean.getCasts().size(); i++) {
        movieDetailBean.getCasts().get(i).setType("??");
      }
      OneMovieDetailActivity.this.runOnUiThread(new Runnable(){
        @Override public void run(){
          setAdapter(movieDetailBean);
        }
      }
);
    }
  }
).start();
}

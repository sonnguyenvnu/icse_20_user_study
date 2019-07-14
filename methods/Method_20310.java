/** 
 * Call this with the latest data when you want models to be rebuilt. The data will be passed on to  {@link #buildModels(Object,Object,Object)}
 */
public void setData(T data1,U data2,V data3){
  this.data1=data1;
  this.data2=data2;
  this.data3=data3;
  allowModelBuildRequests=true;
  requestModelBuild();
  allowModelBuildRequests=false;
}

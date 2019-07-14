public Tank myCglibCreator(){
  Enhancer enhancer=new Enhancer();
  enhancer.setSuperclass(Tank.class);
  enhancer.setCallback(this);
  return (Tank)enhancer.create();
}

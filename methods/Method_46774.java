@Override @SuppressWarnings("squid:S106") public void run(String... args){
  System.out.println(this.cityDao.selectCityById(1));
  System.out.println(this.hotelMapper.selectByCityId(1));
}

public List<InventoryDTO> confirmInline(){
  System.out.println("confirmInline in line for rpc.......");
  List<InventoryDTO> rs=Lists.newArrayList();
  InventoryDTO dto=new InventoryDTO();
  dto.setProductId("1111");
  dto.setCount(9);
  rs.add(dto);
  return rs;
}

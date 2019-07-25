public static ResponseVo error(ResponseStatusEnum statusEnum){
  return vo(statusEnum.getCode(),statusEnum.getMessage(),null);
}

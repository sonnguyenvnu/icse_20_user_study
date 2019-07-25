public static ResponseVo success(ResponseStatusEnum statusEnum){
  return vo(statusEnum.getCode(),statusEnum.getMessage(),null);
}

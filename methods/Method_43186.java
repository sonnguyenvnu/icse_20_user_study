public static ExchangeException adapt(BithumbException e){
  final String message=e.getMessage();
switch (e.getStatus()) {
case "5100":
case "5200":
case "5300":
case "5302":
    return new ExchangeSecurityException(message);
case "5500":
  return new ExchangeException(message);
case "5400":
return new InternalServerException(message);
case "5600":
default :
return new ExchangeException(message);
}
}

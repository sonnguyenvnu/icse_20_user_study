public Device build(Device prototype) throws ValidationException {
  return build(prototype,createDeviceVersion(),baseURL);
}

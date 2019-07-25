/** 
 * Execute <code>runnable</code>, returning a success  {@link Unit} or a failure of the thrown {@link Throwable}.
 * @param sideEffect the runnable
 * @return a new {@link Try} around either a successful {@link Unit} result or the thrown {@link Throwable}
 */
public static Try<Unit> trying(SideEffect sideEffect){
  return trying(() -> {
    IO.io(sideEffect).unsafePerformIO();
    return UNIT;
  }
);
}

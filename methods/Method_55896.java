/** 
 * Initialize the profiling. <p>Using this API user can initialize the CUDA profiler by specifying the configuration file, output file and output file format. This API is generally used to profile different set of counters by looping the kernel launch. The  {@code configFile} parameter can be used to select profiling optionsincluding profiler counters. Refer to the "Compute Command Line Profiler User Guide" for supported profiler options and counters.</p> <p>Limitation: The CUDA profiler cannot be initialized with this API if another profiling tool is already active, as indicated by the {@link CU#CUDA_ERROR_PROFILER_DISABLED} return code.</p><p>Typical usage of the profiling APIs is as follows:</p> <pre><code> for each set of counters/options { cuProfilerInitialize(); //Initialize profiling, set the counters or options in the config file ... cuProfilerStart(); // code to be profiled cuProfilerStop(); ... cuProfilerStart(); // code to be profiled cuProfilerStop(); ... }</code></pre>
 * @param configFile name of the config file that lists the counters/options for profiling
 * @param outputFile name of the outputFile where the profiling results will be stored
 * @param outputMode one of:<br><table><tr><td>{@link #CU_OUT_KEY_VALUE_PAIR OUT_KEY_VALUE_PAIR}</td><td> {@link #CU_OUT_CSV OUT_CSV}</td></tr></table>
 */
@NativeType("CUresult") public static int cuProfilerInitialize(@NativeType("char const *") CharSequence configFile,@NativeType("char const *") CharSequence outputFile,@NativeType("CUoutput_mode") int outputMode){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(configFile,true);
    long configFileEncoded=stack.getPointerAddress();
    stack.nASCII(outputFile,true);
    long outputFileEncoded=stack.getPointerAddress();
    return ncuProfilerInitialize(configFileEncoded,outputFileEncoded,outputMode);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}

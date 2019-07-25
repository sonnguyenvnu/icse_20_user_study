/*
 * Copyright 2003-2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.make;

import jetbrains.mps.compiler.EclipseJavaCompiler;
import jetbrains.mps.compiler.JavaCompilerOptions;
import jetbrains.mps.make.dependencies.StronglyConnectedModules;
import jetbrains.mps.messages.IMessageHandler;
import jetbrains.mps.project.dependency.GlobalModuleDependenciesManager;
import jetbrains.mps.project.dependency.GlobalModuleDependenciesManager.Deptype;
import jetbrains.mps.project.facets.JavaModuleFacet;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.util.performance.IPerformanceTracer.NullPerformanceTracer;
import jetbrains.mps.util.performance.PerformanceTracer;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.util.ProgressMonitor;
import org.jetbrains.mps.openapi.util.SubProgressKind;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * ModuleMaker is able to make sources of the given modules.
 * Main API is two #make methods, one of them accepts also the compiler options argument (e.g. to choose the java language level
 * for the compiler)
 *
 * Underneath this class analyzes module dependencies,
 * chooses which of the modules are dirty, collects all the java sources and handles
 * them to the eclipse java compiler (the mps wrapper {@link EclipseJavaCompiler})
 *
 * fixme use bundle for this package
 * fixme check multiple computations of the same modules' dependencies (time wasting)
 */
public final class ModuleMaker {
  public final static Comparator<SModule> MODULE_BY_NAME_COMPARATOR = Comparator.comparing(SModule::getModuleName);

  private final static String BUILDING_MODULES_MSG = "Building %d Modules";
  private final static String CYCLE_FORMAT_MSG = "Cycle #%d: [%s]";
  private final static String COLLECTING_DEPENDENCIES_MSG = "Collecting Dependent Candidates";
  private final static String LOADING_DEPENDENCIES_MSG = "Loading Dependencies";
  private final static String CALCULATING_DEPENDENCIES_TO_COMPILE_MSG = "Calculating Modules To Compile";
  private final static String BUILDING_MODULE_CYCLES_MSG = "Building Module Cycles";
  private final static String BUILDING_MODULES = "Building";
  private final static String BUILDING_BACK_DEPS_MSG = "Building Closure";
  private final static String BUILDING_DIRTY_CLOSURE = "Dirty Modules";
  private final static String CHECKING_DIRTY_MODULES_MSG = "Checking";

  @NotNull private final CompositeTracer myTracer;

  /**
   * The empty constructor delegates only error messages to the apache's logger and traces nothing
   */
  public ModuleMaker() {
    Logger logger = LogManager.getLogger(ModuleMaker.class);
    MessageSender sender = new MessageSender(IMessageHandler.NULL_HANDLER, logger, this, Level.ERROR);
    myTracer = new CompositeTracer(new NullPerformanceTracer(), sender);
  }

  /**
   * Constructor for regular use, if uncertain, use this one.
   *
   * @param handler sink for end-user messages
   */
  public ModuleMaker(@NotNull IMessageHandler handler) {
    // End-user messages piped through supplied handler, trace and debug messages go to log according to external configuration
    Logger logger = LogManager.getLogger(ModuleMaker.class);
    String mmName = ModuleMaker.class.getName();
    MessageSender sender = new MessageSender(handler, logger, mmName, Level.ALL);
    // PerformanceTracer.printReport sends it with info level, but it doesn't seem reasonable to collect performance data unless we debug MM.
    myTracer = new CompositeTracer(logger.isDebugEnabled() ? new PerformanceTracer(mmName) : new NullPerformanceTracer(), sender);
  }

  /**
   * TODO move or rename the ModuleMaker (the naming is quite disturbing)
   */
  public void clean(final Set<? extends SModule> modules, @NotNull final ProgressMonitor monitor) {
    monitor.start("Cleaning...", modules.size());
    try {
      for (SModule module : modules) {
        if (monitor.isCanceled()) {
          break;
        }
        if (!ModulesContainer.isExcluded(module)) {
          monitor.step(module.getModuleName());
          JavaModuleFacet facet = module.getFacet(JavaModuleFacet.class);
          assert facet != null && facet.getClassesGen() != null;
          File classesGenFile = new File(facet.getClassesGen().getPath());
          FileUtil.delete(classesGenFile);
        }
        monitor.advance(1);
      }
    } finally {
      monitor.done();
    }
  }

  @NotNull
  public MPSCompilationResult make(final Collection<? extends SModule> modules, @NotNull final ProgressMonitor monitor) {
    return make(modules, monitor, null);
  }

  @NotNull
  public MPSCompilationResult make(final Collection<? extends SModule> modules, @NotNull final ProgressMonitor monitor, @Nullable final JavaCompilerOptions compilerOptions) {
    CompositeTracer tracer = new CompositeTracer(myTracer, monitor);
    tracer.start(String.format(BUILDING_MODULES_MSG, modules.size()), 10);
    try {
      tracer.push(COLLECTING_DEPENDENCIES_MSG);
      Set<SModule> candidates = new LinkedHashSet<>(new GlobalModuleDependenciesManager(modules).getModules(Deptype.COMPILE));
      tracer.pop(1);

      tracer.push(LOADING_DEPENDENCIES_MSG);
      Dependencies dependencies = new Dependencies(candidates); // fixme AP why do we need to look for some other deps??
      tracer.pop(1);

      tracer.push(CALCULATING_DEPENDENCIES_TO_COMPILE_MSG);
      final ModulesContainer modulesContainer = new ModulesContainer(candidates, dependencies);
      Set<SModule> toCompile = buildDirtyModulesClosure(modulesContainer, tracer.subTracer(1));
      tracer.pop();

      tracer.push(BUILDING_MODULE_CYCLES_MSG);
      List<Set<SModule>> schedule = new StronglyConnectedModules<>(toCompile).getStronglyConnectedComponents();
      tracer.pop(1);

      return compileCycles(compilerOptions, schedule, tracer.subTracer(6, SubProgressKind.REPLACING), modulesContainer);
    } finally {
      tracer.done();
      tracer.printReport();
    }
  }

  @NotNull
  private MPSCompilationResult compileCycles(@Nullable JavaCompilerOptions compilerOptions, List<Set<SModule>> cyclesToCompile, @NotNull CompositeTracer tracer, @NotNull ModulesContainer allModules) {
    List<MPSCompilationResult> cycleCompilationResults = new ArrayList<>();
    tracer.start("", cyclesToCompile.size());
    try {
      int cycleNumber = 0;
      for (Set<SModule> modulesInCycle : cyclesToCompile) {
        if (tracer.isMonitorCanceled()) {
          break;
        }
        ++cycleNumber;
        CompositeTracer cycleTracer = tracer.subTracer(1);
        tracer.getSender().info(String.format(CYCLE_FORMAT_MSG, cycleNumber, modulesInCycle));
        cycleTracer.start(getCycleString(cycleNumber, modulesInCycle), 1);
        ModulesContainer modulesContainer = allModules.restricted(modulesInCycle);
        InternalJavaCompiler internalJavaCompiler = new InternalJavaCompiler(modulesContainer, compilerOptions);
        MPSCompilationResult cycleCompilationResult = internalJavaCompiler.compile(cycleTracer.subTracer(1, SubProgressKind.AS_COMMENT));
        cycleCompilationResults.add(cycleCompilationResult);
        cycleTracer.done(0);
      }
    } finally {
      tracer.done();
    }

    return combineCycleCompilationResults(cycleCompilationResults);
  }

  private String getCycleString(int cycleNumber, Set<SModule> modulesInCycle) {
    Optional<SModule> first = modulesInCycle.stream().findFirst();
    String firstModule = "";
    if (first.isPresent()) {
      firstModule = first.get().getModuleName();
      if (modulesInCycle.size() > 1) {
        firstModule += " and " + (modulesInCycle.size() - 1) + " others";
      }
    }
    return String.format(CYCLE_FORMAT_MSG, cycleNumber, firstModule);
  }

  @NotNull
  private MPSCompilationResult combineCycleCompilationResults(List<MPSCompilationResult> results) {
    int errorCount = 0;
    int warnCount = 0;
    Set<SModule> changedModules = new HashSet<>();
    for (MPSCompilationResult result : results) {
      errorCount += result.getErrorsCount();
      warnCount += result.getWarningsCount();
      changedModules.addAll(result.getChangedModules());
    }
    return new MPSCompilationResult(errorCount, warnCount, false, changedModules);
  }

  /**
   * The answer is always sorted by name
   */
  private Set<SModule> buildDirtyModulesClosure(ModulesContainer modulesContainer, CompositeTracer tracer) {
    tracer.start(BUILDING_DIRTY_CLOSURE, 3);
    Set<SModule> candidates = modulesContainer.getModules();
    tracer.push(CHECKING_DIRTY_MODULES_MSG);
    List<SModule> dirtyModules = new ArrayList<>(candidates.size());
    for (ModuleSources m : modulesContainer.getDirtyModuleSources()) {
      dirtyModules.add(m.getModule());
    }
    tracer.pop(1);

    // select from modules those that are affected by the "dirty" modules
    // M={m}, D={m*}, D<=M, R:M->2^M (required), R* transitive closure of R
    // C={m|m from M, exists m* from D: m* in R*(m)}
    // to compile T=D union C

    Map<SModule, Set<SModule>> backDependencies = new HashMap<>();

    tracer.push(BUILDING_BACK_DEPS_MSG);
    for (SModule m : candidates) {
      for (SModule dep : new GlobalModuleDependenciesManager(m).getModules(Deptype.COMPILE)) {
        Set<SModule> incoming = backDependencies.get(dep);
        if (incoming == null) {
          incoming = new HashSet<>();
          backDependencies.put(dep, incoming);
        }
        incoming.add(m);
      }
    }
    Set<SModule> toCompile = new LinkedHashSet<>();
    // BFS from dirtyModules along backDependencies
    LinkedList<SModule> queue = new LinkedList<>(dirtyModules);
    while (!queue.isEmpty()) {
      SModule m = queue.removeFirst();
      if (candidates.contains(m)) {
        toCompile.add(m);
      }
      Set<SModule> backDeps = backDependencies.remove(m);
      if (backDeps != null) {
        queue.addAll(backDeps);
      }
    }
    tracer.pop(1);

    Set<SModule> result = new TreeSet<>(MODULE_BY_NAME_COMPARATOR);
    result.addAll(toCompile);
    return result;
  }
}

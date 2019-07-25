/*
 * Copyright 2003-2018 JetBrains s.r.o.
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
package jetbrains.mps.typesystem.inference;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.lang.typesystem.runtime.RuntimeSupport;
import jetbrains.mps.lang.typesystem.runtime.performance.RuntimeSupport_Tracer;
import jetbrains.mps.lang.typesystem.runtime.performance.SubtypingManager_Tracer;
import jetbrains.mps.newTypesystem.RuntimeSupportNew;
import jetbrains.mps.newTypesystem.SubTypingManagerNew;
import jetbrains.mps.newTypesystem.context.HoleTypecheckingContext;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.smodel.language.LanguageRegistryListener;
import jetbrains.mps.smodel.language.LanguageRuntime;
import jetbrains.mps.typesystem.TypeSystemReporter;
import jetbrains.mps.typesystem.inference.util.ConcurrentSubtypingCache;
import jetbrains.mps.typesystem.inference.util.SubtypingCache;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.performance.IPerformanceTracer;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayList;
import java.util.List;

public class TypeChecker implements CoreComponent, LanguageRegistryListener {
  private static TypeChecker INSTANCE;

  public final Object LISTENERS_LOCK = new Object();

  //TypeChecker is a singleton, so we can omit remove() here though the field is not static
  private ThreadLocal<TypesReadListener> myTypesReadListener = new ThreadLocal<>();
  private List<TypeRecalculatedListener> myTypeRecalculatedListeners = new ArrayList<>(5);

  private final LanguageRegistry myLanguageRegistry;

  private IPerformanceTracer myPerformanceTracer = null;

  private SubtypingManager mySubtypingManager;
  private SubtypingManager mySubtypingManagerTracer;

  private RuntimeSupport myRuntimeSupport;
  private RuntimeSupport myRuntimeSupportTracer;

  private RulesManager myRulesManager;

  private SubtypingCache myGenerationSubTypingCache = null;

  private ThreadLocal<Boolean> myIsGenerationThread = new ThreadLocal<Boolean>() {
    @Override
    protected Boolean initialValue() {
      return Boolean.FALSE;
    }
  };
  private Thread myMainGenerationThread;

  public TypeChecker(LanguageRegistry languageRegistry) {
    myLanguageRegistry = languageRegistry;
    myRuntimeSupport = new RuntimeSupportNew(this);
    mySubtypingManager = new SubTypingManagerNew(this);
    myRulesManager = new RulesManager(this);
    myRuntimeSupportTracer = new RuntimeSupport_Tracer(this);
    mySubtypingManagerTracer = new SubtypingManager_Tracer(this);
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }

    INSTANCE = this;
    myLanguageRegistry.addRegistryListener(this);
  }

  @Override
  public void dispose() {
    myLanguageRegistry.removeRegistryListener(this);
    INSTANCE = null;
  }

  @Override
  public void afterLanguagesLoaded(Iterable<LanguageRuntime> languages) {
    myRulesManager.loadLanguages(languages);
  }

  @Override
  public void beforeLanguagesUnloaded(Iterable<LanguageRuntime> languages) {
    myRulesManager.unloadLanguages(languages);
  }

  /*package*/ LanguageRegistry getLanguageRegistry() {
    return myLanguageRegistry;
  }

  public static TypeChecker getInstance() {
    return INSTANCE;
  }

  private boolean isMainGenerationThread() {
    return Thread.currentThread() == myMainGenerationThread;
  }

  public SubtypingManager getSubtypingManager() {
    if (isMainGenerationThread()) {
      return mySubtypingManagerTracer;
    }
    return mySubtypingManager;
  }

  public RuntimeSupport getRuntimeSupport() {
    if (isMainGenerationThread()) {
      return myRuntimeSupportTracer;
    }
    return myRuntimeSupport;
  }

  public SubtypingCache getSubtypingCache() {
    if (isGenerationMode()) {
      SubtypingCache generationSubTypingCache = myGenerationSubTypingCache;
      if (generationSubTypingCache != null) {
        return generationSubTypingCache;
      }
    }
    return TypeContextManager.getInstance().getSubtypingCache();
  }

  public RulesManager getRulesManager() {
    return myRulesManager;
  }

  private SubtypingCache createSubtypingCache() {
    return new ConcurrentSubtypingCache();
  }

  public void generationStarted(IPerformanceTracer performanceTracer) {
    myGenerationSubTypingCache = createSubtypingCache();
    initTracing(performanceTracer);
    myIsGenerationThread.set(Boolean.TRUE);
    myMainGenerationThread = Thread.currentThread();
  }

  public void generationFinished() {
    myGenerationSubTypingCache = null;
    disposeTracing();
    myIsGenerationThread.set(Boolean.FALSE);
    myMainGenerationThread = null;
  }

  public void generationWorkerStarted() {
    myIsGenerationThread.set(Boolean.TRUE);
  }

  public void generationWorkerFinished() {
    myIsGenerationThread.set(Boolean.FALSE);
  }

  public boolean isGenerationMode() {
    return myIsGenerationThread.get();
  }

  private void initTracing(IPerformanceTracer performanceTracer) {
    if (performanceTracer != null) {
      myPerformanceTracer = performanceTracer;
      TypeSystemReporter.getInstance().reset();
    }
  }

  private void disposeTracing() {
    if (myPerformanceTracer != null) {
      TypeSystemReporter.getInstance().printReport(10, myPerformanceTracer);
      myPerformanceTracer = null;
    }
  }

  public boolean hasPerformanceTracer() {
    return myPerformanceTracer != null;
  }

  public <T> T computeWithTrace(Computable<T> c, String taskName) {
    if (myPerformanceTracer != null) {
      try {
        myPerformanceTracer.push(taskName);
        return c.compute();
      } finally {
        myPerformanceTracer.pop();
      }
    } else {
      return c.compute();
    }
  }

  public InequalitySystem getInequalitiesForHole(SNode hole, boolean holeIsAType) {
    HoleTypecheckingContext typeCheckingContext = TypeContextManager.getInstance().createHoleTypecheckingContext(hole);
    InequalitySystem inequalitySystem = typeCheckingContext.getTypechecking().computeInequalitiesForHole(hole, holeIsAType);
    typeCheckingContext.dispose();
    return inequalitySystem;
  }

  public SNode getInferredTypeOf(final SNode node) {
    if (node == null) return null;
    TypeCheckingContext typeCheckingContext = TypeContextManager.getInstance().createInferenceTypeCheckingContext(node);
    SNode type = typeCheckingContext.computeTypeInferenceMode(node);
    typeCheckingContext.dispose();
    return type;
  }

  @Nullable
  public SNode getTypeOf(final SNode node) {
    if (node == null || node.getModel() == null) return null;
    fireNodeTypeAccessed(node);
    return TypeContextManager.getInstance().getTypeOf(node);
  }


  private List<TypeRecalculatedListener> copyTypeRecalculatedListeners() {
    synchronized (LISTENERS_LOCK) {
      return new ArrayList<>(myTypeRecalculatedListeners);
    }
  }

  public void addTypesReadListener(TypesReadListener typesReadListener) {
    assert myTypesReadListener.get() == null;
    myTypesReadListener.set(typesReadListener);
  }

  public void removeTypesReadListener(TypesReadListener typesReadListener) {
    assert myTypesReadListener.get() == typesReadListener;
    myTypesReadListener.set(null);
  }

  public void removeTypeRecalculatedListener(TypeRecalculatedListener typeRecalculatedListener) {
    synchronized (LISTENERS_LOCK) {
      myTypeRecalculatedListeners.remove(typeRecalculatedListener);
    }
  }

  public void addTypeRecalculatedListener(TypeRecalculatedListener typeRecalculatedListener) {
    synchronized (LISTENERS_LOCK) {
      if (!myTypeRecalculatedListeners.contains(typeRecalculatedListener)) {
        myTypeRecalculatedListeners.add(typeRecalculatedListener);
      }
    }
  }

  public void fireTypeWillBeRecalculatedForTerm(SNode term) {
    for (TypeRecalculatedListener typeRecalculatedListener : copyTypeRecalculatedListeners()) {
      typeRecalculatedListener.typeWillBeRecalculatedForTerm(term);
    }
  }

  private void fireNodeTypeAccessed(SNode term) {
    TypesReadListener typesReadListener = myTypesReadListener.get();
    if (typesReadListener != null) {
      typesReadListener.nodeTypeAccessed(term);
    }
  }

}

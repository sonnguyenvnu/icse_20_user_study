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

import jetbrains.mps.classloading.ClassLoaderManager;
import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.newTypesystem.context.CachingTypecheckingContext;
import jetbrains.mps.newTypesystem.context.HoleTypecheckingContext;
import jetbrains.mps.newTypesystem.context.IncrementalTypecheckingContext;
import jetbrains.mps.newTypesystem.context.InferenceTypecheckingContext;
import jetbrains.mps.newTypesystem.context.TargetTypecheckingContext;
import jetbrains.mps.newTypesystem.context.TargetTypecheckingContext_Tracer;
import jetbrains.mps.newTypesystem.context.TracingTypecheckingContext;
import jetbrains.mps.typesystem.inference.ITypechecking.Action;
import jetbrains.mps.typesystem.inference.ITypechecking.Computation;
import jetbrains.mps.typesystem.inference.util.SubtypingCache;
import jetbrains.mps.util.Computable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @deprecated {@link jetbrains.mps.typechecking.TypecheckingFacade} should be used for executing typechecking actions.
 */
@Deprecated
public class TypeContextManager implements CoreComponent {

  private static final Logger LOG = LogManager.getLogger(TypeContextManager.class);

  private static TypeContextManager INSTANCE;
  private static boolean myReported = false;

  private final Object myLock = new Object();
  private Map<ITypeContextOwner, TypecheckingContextHolder> myTypeCheckingContexts = Collections.synchronizedMap(new HashMap<>());

  // dependencies
  private final TypeChecker myTypeChecker;
  private final ClassLoaderManager myClassLoaderManager;

  private AtomicBoolean myDisposeRequested = new AtomicBoolean(false);
  private AtomicInteger myExecuting = new AtomicInteger(0);

  //TypeContextManager is a singleton, so we can omit remove() here though the field is not static
  private ThreadLocal<ITypeContextOwner> myTypecheckingContextOwner = new ThreadLocal<ITypeContextOwner>() {
    @Override
    protected ITypeContextOwner initialValue() {
      return new DefaultTypecheckingContextOwner() {
        @Override
        public boolean reuseTypecheckingContext() {
          return false;
        }
      };
    }
  };
  private ThreadLocal<SubtypingCache> mySubtypingCache = new ThreadLocal<>();

  /**
   * @deprecated use {@link jetbrains.mps.components.ComponentHost#findComponent(Class)} instead.
   */
  @Deprecated
  public static TypeContextManager getInstance() {
    return INSTANCE;
  }

  public TypeContextManager(TypeChecker typeChecker, ClassLoaderManager classLoaderManager) {
    myTypeChecker = typeChecker;
    myClassLoaderManager = classLoaderManager;
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }

    INSTANCE = this;
  }

  @Override
  public void dispose() {
    // signal to the Executor
    if (!myDisposeRequested.compareAndSet(false, true)) return;

    // if busy, bail
    if (myExecuting.get() > 0) return;

    doDispose();
  }

  private void doDispose() {
    for (Map.Entry<ITypeContextOwner, TypecheckingContextHolder> entry: myTypeCheckingContexts.entrySet()) {
      entry.getValue().dispose();
    }
    myTypeCheckingContexts.clear();
    INSTANCE = null;
  }

  public void runTypeCheckingAction(SNode node, ITypechecking.Action r) {
    new Executor<>(node, r).execute();
  }

  public void runTypeCheckingAction(@NotNull final ITypeContextOwner contextOwner, SNode node, ITypechecking.Action r) {
    new Executor<>(contextOwner, node, r).execute();
  }

  public <T> T runTypeCheckingComputation(@NotNull final ITypeContextOwner contextOwner, SNode node, Computation<T> r) {
    return new Executor<>(contextOwner, node, r).execute();
  }

  public void runResolveAction(Runnable r) {
    runTypecheckingAction(new NonReusableTypecheckingContextOwner(), r);
  }

  public <T> T runResolveAction(Computable<T> computable) {
    return runTypecheckingAction(new NonReusableTypecheckingContextOwner(), computable);
  }

  public void runTypecheckingAction(ITypeContextOwner contextOwner, Runnable r) {
    new Executor<>(contextOwner, r).execute();
  }

  public <T> T runTypecheckingAction(ITypeContextOwner contextOwner, Computable<T> computable) {
    return new Executor<>(contextOwner, computable).execute();
  }

  public TypeCheckingContext createTypeCheckingContext(SNode node) {
    if (myTypeChecker.isGenerationMode()) {
      return new TargetTypecheckingContext(node, myTypeChecker);
    } else {
      return new IncrementalTypecheckingContext(node, myTypeChecker, myClassLoaderManager);
    }
  }

  public HoleTypecheckingContext createHoleTypecheckingContext(SNode node) {
    return new HoleTypecheckingContext(node, myTypeChecker);
  }

  public TypeCheckingContext createInferenceTypeCheckingContext(SNode node) {
    return new InferenceTypecheckingContext(node, myTypeChecker);
  }

  public TypeCheckingContext createTracingTypeCheckingContext(SNode node) {
    return new TracingTypecheckingContext(node, myTypeChecker);
  }

  public TypeCheckingContext acquireTypecheckingContext(SNode node, ITypeContextOwner contextOwner) {
    return getOrCreateContext(node, contextOwner);
  }

  public void releaseTypecheckingContext(ITypeContextOwner contextOwner) {
    //if node is disposed, then context was removed by beforeModelDisposed/beforeRootDeleted listener
    synchronized (myLock) {
      TypecheckingContextHolder contextHolder = myTypeCheckingContexts.get(contextOwner);
      if (contextHolder == null) return;

      ITypeContextOwner o = contextHolder.getOwner();
      if (o == contextOwner) {
        contextHolder.release();
        if (!contextHolder.isActive()) {
          myTypeCheckingContexts.remove(contextOwner);
        }
      }
    }
  }


  private class Executor<T> {
    private ITypeContextOwner myContextOwner;
    private SNode myContextNode;
    private Action myAction;
    private Computable<T> myComputable;
    private Computation<T> myComputation;
    private Runnable myRunnable;

    Executor(ITypechecking.Action action) {
      myAction = action;
    }
    Executor(SNode contextNode, ITypechecking.Action action) {
      myContextNode = contextNode;
      myAction = action;
    }
    Executor(ITypeContextOwner contextOwner, SNode contextNode, ITypechecking.Action action) {
      myContextOwner = contextOwner;
      myContextNode = contextNode;
      myAction = action;
    }
    Executor(ITypeContextOwner contextOwner, SNode contextNode,  Computation<T> computation) {
      myContextOwner = contextOwner;
      myContextNode = contextNode;
      myComputation = computation;
    }
    Executor(ITypeContextOwner contextOwner, Computable<T> computable) {
      myContextOwner = contextOwner;
      myComputable = computable;
    }
    Executor(ITypeContextOwner contextOwner, Runnable runnable) {
      myContextOwner = contextOwner;
      myRunnable = runnable;
    }
    T execute() {
      // one more task executing
      myExecuting.incrementAndGet();

      // dispose has been called? no-no-no
      if (myDisposeRequested.get()) {
        if (myExecuting.decrementAndGet() == 0) {
          doDispose();
        }
        return null;
      }

      final ITypeContextOwner savedOwner = myTypecheckingContextOwner.get();
      if (myContextOwner != null) {
        myTypecheckingContextOwner.set(myContextOwner);
      }

      final ITypeContextOwner contextOwner = myTypecheckingContextOwner.get();

      TypeCheckingContext context = null;
      if (myContextNode != null) {
        context = acquireTypecheckingContext(myContextNode, contextOwner);
      }

      final SubtypingCache savedSubtypingCache = mySubtypingCache.get();
      mySubtypingCache.set(null);

      try {
        return doExecute(context);
      }
      finally {
        mySubtypingCache.set(savedSubtypingCache);

        if (context != null) {
          releaseTypecheckingContext(contextOwner);
        }

        if (myContextOwner != null) {
          myTypecheckingContextOwner.set(savedOwner);
        }

        // do dispose on last task finished
        int executingTasks = myExecuting.decrementAndGet();
        if (myDisposeRequested.get() && executingTasks == 0) {
          doDispose();
        }
      }
    }

    private T doExecute(TypeCheckingContext context) {
      if (myAction != null) {
        myAction.run(context);
        return null;
      }
      else if (myComputation != null) {
        return myComputation.compute(context);
      }
      else if (myComputable != null) {
        return myComputable.compute();
      }
      else if (myRunnable != null) {
        myRunnable.run();
        return null;
      }
      throw new IllegalStateException();
    }
  }


  private TypeCheckingContext getOrCreateContext(SNode node, ITypeContextOwner owner) {
    if (node == null) return null;
    final SModel model = node.getModel();
    assert model != null;

    synchronized (myLock) {
      final TypecheckingContextHolder contextHolder = myTypeCheckingContexts.get(owner);

      if (contextHolder != null) {
        // reuse the typechecking context
        return contextHolder.acquire(node);
      }
      else {
        // not found, create new
        if (!owner.reuseTypecheckingContext()) {
          final NonReusableTypecheckingContextHolder newContextHolder = new NonReusableTypecheckingContextHolder(owner);
          myTypeCheckingContexts.put(owner, newContextHolder);
          return newContextHolder.acquire(node);

        }
        else {
          final CountingTypecheckingContextHolder newContextHolder = new CountingTypecheckingContextHolder(owner);
          myTypeCheckingContexts.put(owner, newContextHolder);
          return newContextHolder.acquire(node);
        }
      }
    }
  }

  public TypeCheckingContext createTypeCheckingContextForResolve(SNode node) {
    SNode root = node.getContainingRoot();
    return new CachingTypecheckingContext(root, myTypeChecker);
  }

  /*package*/ SubtypingCache getSubtypingCache() {
    final SubtypingCache subtypingCache = mySubtypingCache.get();
    if (subtypingCache != null) return subtypingCache;

    final ITypeContextOwner typeContextOwner = myTypecheckingContextOwner.get();
    final SubtypingCache newSubtypingCache = typeContextOwner.createSubtypingCache();
    if (newSubtypingCache != null) {
      mySubtypingCache.set(newSubtypingCache);
    }
    return newSubtypingCache;
  }

  @Nullable
  /*package*/ SNode getTypeOf(final SNode node) {
    if (node == null) return null;

    if (myTypeChecker.isGenerationMode()) {
      TypeCheckingContext context = myTypeChecker.hasPerformanceTracer() ?
          new TargetTypecheckingContext_Tracer(node, myTypeChecker) :
          new TargetTypecheckingContext(node, myTypeChecker);
      try {
        return context.getTypeOf_generationMode(node);
      } finally {
        context.dispose();
      }
    }
    //now we are not in generation mode
    final ITypeContextOwner contextOwner = myTypecheckingContextOwner.get();

    return new Executor<>(contextOwner, node, context -> context != null ? context.getTypeOf(node, myTypeChecker) : null).execute();
  }

  private interface TypecheckingContextHolder {
    ITypeContextOwner getOwner();

    void clear();

    void dispose();

    TypeCheckingContext acquire(SNode node);

    void release();

    boolean isActive();
  }

  private class CountingTypecheckingContextHolder implements TypecheckingContextHolder {
    private final ITypeContextOwner myOwner;
    private AtomicReference<TypeCheckingContext> myContext = new AtomicReference<>(null);
    private Map<Thread, Integer> myCounters = Collections.synchronizedMap(new HashMap<>());

    CountingTypecheckingContextHolder(ITypeContextOwner owner) {
      myOwner = owner;
    }

    @Override
    public ITypeContextOwner getOwner() {
      return myOwner;
    }

    @Override
    public void clear() {
      if (myContext.get() != null) {
        myContext.get().clear();
      }
    }

    @Override
    public void dispose() {
      if (myContext.get() != null) {
        myContext.get().dispose();
      }
      myContext.set(null);
      myCounters.clear();
    }

    @Override
    public void release() {
      Integer ctr = myCounters.get(Thread.currentThread());
      if (ctr == null) return;

      if ((ctr -= 1) == 0) {
        myCounters.remove(Thread.currentThread());
        if (myCounters.isEmpty()) {
          myContext.get().dispose();
          myContext.set(null);
        }
      }
      else {
        myCounters.put(Thread.currentThread(), ctr);
      }
    }

    @Override
    public TypeCheckingContext acquire(SNode node) {
      if (myContext.get() == null) {
        myContext.set(myOwner.createTypecheckingContext(node, TypeContextManager.this));
        myCounters.put(Thread.currentThread(), 1);
      }
      else {
        Integer ctr = myCounters.get(Thread.currentThread());
        myCounters.put(Thread.currentThread(), (ctr != null ? ctr : 0) + 1);
      }

      return myContext.get();
    }

    @Override
    public boolean isActive() {
      return !myCounters.isEmpty();
    }
  }

  private class NonReusableTypecheckingContextHolder implements TypecheckingContextHolder {

    private LinkedList<TypeCheckingContext> myContexts = new LinkedList<>();
    private ITypeContextOwner myOwner;

    NonReusableTypecheckingContextHolder(ITypeContextOwner owner) {
      myOwner = owner;
    }

    @Override
    public ITypeContextOwner getOwner() {
      return myOwner;
    }

    @Override
    public void clear() {
      for (TypeCheckingContext context : myContexts) {
        context.clear();
      }
    }

    @Override
    public void dispose() {
      for (TypeCheckingContext context : myContexts) {
        context.dispose();
      }
      myContexts.clear();
    }

    @Override
    public TypeCheckingContext acquire(SNode node) {
      if (myContexts.size() >= 10) {
        LOG.warn("too many non-reusable typechecking contexts");
        return null;
      }
      for (TypeCheckingContext ctx : myContexts) {
        if (ctx.getNode() == node) {
          LOG.warn("double typechecking context acquiring");
          return null;
        }
      }

      final TypeCheckingContext ctx = myOwner.createTypecheckingContext(node, TypeContextManager.this);
      myContexts.add(ctx);

      return ctx;
    }

    @Override
    public void release() {
      if (!myContexts.isEmpty()) {
        final TypeCheckingContext ctx = myContexts.removeLast();
        ctx.dispose();
      }
    }

    @Override
    public boolean isActive() {
      return !myContexts.isEmpty();
    }
  }

}

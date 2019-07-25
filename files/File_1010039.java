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
package jetbrains.mps.core.aspects.behaviour;

import jetbrains.mps.core.aspects.behaviour.api.BHDescriptor;
import jetbrains.mps.core.aspects.behaviour.api.BHMethodImplementationIsNotFoundException;
import jetbrains.mps.core.aspects.behaviour.api.BHMethodIsNotFoundInVTable;
import jetbrains.mps.core.aspects.behaviour.api.BHMethodNotFoundException;
import jetbrains.mps.core.aspects.behaviour.api.BehaviorRegistry;
import jetbrains.mps.core.aspects.behaviour.api.SAbstractType;
import jetbrains.mps.core.aspects.behaviour.api.SConstructor;
import jetbrains.mps.core.aspects.behaviour.api.SMethod;
import jetbrains.mps.core.aspects.behaviour.api.SParameter;
import jetbrains.mps.smodel.SModelUtil_new;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static jetbrains.mps.core.aspects.behaviour.BehaviorChecker.checkForConcept;
import static jetbrains.mps.core.aspects.behaviour.BehaviorChecker.checkNotStatic;
import static jetbrains.mps.core.aspects.behaviour.BehaviorChecker.checkParameters;
import static jetbrains.mps.core.aspects.behaviour.BehaviorChecker.checkStatic;

/**
 * Common ancestor for all the generated behavior aspects (per concept).
 * Exploiting the idea of virtual table to yield the dynamic dispatch for behavior methods' invocation.
 * <p>
 * TODO
 * Features:
 * Multiple dispatch?
 * Default parameter values?
 *
 * @author apyshkin
 */
public abstract class BaseBHDescriptor implements BHDescriptor {
  private final SMethodVirtualTable mySuperVTable = new SMethodVirtualTable();
  private BehaviorRegistry myBehaviorRegistry;
  private final AtomicReference<List<SMethod<?>>> myCachedMethods = new AtomicReference<>(); // optimization by ashatalin

  private SAbstractConcept myConcept;
  private boolean myInitialized = false;
  private SMethodVirtualTable myVTable;
  private AncestorCache myAncestorCache;

  /**
   * @deprecated shall use no-arg cons instead, and pass BehaviorRegistry through init()
   *  shall survive 2019.2 release to ensure code generated/compiled with previous MPS releases works (ensures binary compatibility)
   */
  @Deprecated
  @ToRemove(version = 2019.2)
  protected BaseBHDescriptor(BehaviorRegistry behaviorRegistry) {
    myBehaviorRegistry = behaviorRegistry;
  }

  protected BaseBHDescriptor() {
    // since 2019.2
  }


  /**
   * Intended to be executed during concept behavior construction
   *
   * @see BehaviorRegistry#getBHDescriptor
   */
  public synchronized void init(@NotNull BehaviorRegistry registry) {
    if (!myInitialized) {
      myBehaviorRegistry = registry;
      myConcept = getConcept();
      myAncestorCache = new AncestorCache(myConcept, myBehaviorRegistry);
      initVirtualTables();
      myInitialized = true;
    }
  }

  private void checkDescriptorIsInitialized() {
    if (!myInitialized) {
      throw new BHNotInitializedException(myConcept);
    }
  }

  private void initVirtualTables() {
    myVTable = new SMethodVirtualTable(getDeclaredMethods());
    List<SAbstractConcept> ancestors = myAncestorCache.getAncestorsInvocationOrder();
    for (SAbstractConcept ancestor : ancestors) {
      if (ancestor != myConcept) {
        BHDescriptor bhDescriptor = getBHDescriptor(ancestor);
        myVTable.merge(bhDescriptor);
        mySuperVTable.merge(bhDescriptor);
      }
    }
  }

  @NotNull
  private BHDescriptor getBHDescriptor(@NotNull SAbstractConcept concept) {
    if (concept.equals(myConcept)) {
      return this;
    }
    return myBehaviorRegistry.getBHDescriptor(concept);
  }

  static class ParametersTypeConverter {
    private final List<SParameter> myMethodParameters;

    ParametersTypeConverter(@NotNull List<SParameter> methodParameters) {
      myMethodParameters = methodParameters;
    }

    private SParameter getLastParameter() {
      return myMethodParameters.get(myMethodParameters.size() - 1);
    }

    public Object[] convertParameters(Object... parameters) {
      if (parameters == null) {
        return new Object[]{null};
      }
      if (myMethodParameters.isEmpty()) {
        return new Object[0];
      }
      Object[] newParameters;
      SParameter lastPrm = getLastParameter();
      if (lastPrm instanceof SVarArgParameter) {
        newParameters = resolveVarArg(parameters);
      } else {
        newParameters = resolveSingleArrayArgumentProblem(parameters);
      }
      return newParameters;
    }

    @NotNull
    private Object[] resolveVarArg(@NotNull Object[] parameters) {
      SVarArgParameter lastPrm = (SVarArgParameter) getLastParameter();
      Object[] newParameters = new Object[myMethodParameters.size()];
      SAbstractType componentType = lastPrm.getComponentType();
      Class<?> javaComponentType = Object.class;
      if (componentType instanceof SJavaCompoundType) {
        javaComponentType = ((SJavaCompoundType) componentType).getJavaType();
      }
      newParameters[myMethodParameters.size() - 1] = Array.newInstance(javaComponentType, parameters.length - myMethodParameters.size() + 1);
      for (int i = 0; i < parameters.length; ++i) {
        if (i < myMethodParameters.size() - 1) {
          newParameters[i] = parameters[i];
        } else {
          Array.set(newParameters[myMethodParameters.size() - 1], i - myMethodParameters.size() + 1, parameters[i]);
        }
      }
      return newParameters;
    }

    @NotNull
    private Object[] resolveSingleArrayArgumentProblem(@NotNull Object[] parameters) {
      SParameter lastPrm = getLastParameter();
      if (myMethodParameters.size() == 1) { // that means that we could be passing a single array
        if (lastPrm.getType() instanceof SJavaCompoundType) {
          Class<?> javaType = ((SJavaCompoundType) lastPrm.getType()).getJavaType();
          if (javaType.isArray()) {
            Class<?> componentType = javaType.getComponentType();
            for (Object parameter : parameters) {
              if (parameter == null) {
                continue;
              }
              if (!componentType.isAssignableFrom(parameter.getClass())) {
                return parameters;
              }
            }
            parameters = new Object[]{parameters};
          }
        }
      }
      return parameters;
    }
  }

  /**
   * in the case of the last vararg argument converts all arguments into arguments + separate array for the vararg arguments
   * also used against a single null in the varargs arguments
   */
  @Nullable
  private Object[] getParametersArray(@NotNull List<SParameter> methodParameters, Object... parameters) {
    return new ParametersTypeConverter(methodParameters).convertParameters(parameters);
  }

  @NotNull
  @Override
  public SNode newNode(@Nullable SModel model, @NotNull SConstructor constructor, Object... parameters) {
    if (parameters.length > 0) {
      throw new IllegalArgumentException("For now one cannot pass arguments to a behavior constructor");
    }
    if (constructor.getConcept() != getConcept()) {
      throw new IllegalArgumentException("Concept of the passed constructor and the concept of the descriptor must coincide");
    }
    SNode node = SModelUtil_new.instantiateConceptDeclaration(myConcept, model, null, false);
    new ConstructionHandler(myAncestorCache, myConcept).initNode(node, constructor, getParametersArray(Collections.emptyList(), parameters));
    return node;
  }

  /**
   * Though status of node construction is not clear, we keep this API due to legacy reasons.
   * There are already several similar approaches to construct a node and not everybody agrees that
   * the main point for this activity is here, behavior rt.
   */
  @Override
  public void initNode(@NotNull SNode node) {
    SConstructor defaultConstructor = new SDefaultConstructorImpl(this, AccessPrivileges.PUBLIC);
    Object[] emptyParameters = new Object[0];
    new ConstructionHandler(myAncestorCache, myConcept).initNode(node, defaultConstructor,
                                                                 getParametersArray(Collections.emptyList(), emptyParameters));
  }

  @Override
  public final <T> T invoke(@NotNull SNode operand, @NotNull SMethod<T> method, Object... parameters) {
    checkDescriptorIsInitialized();
    checkNotStatic(method);
    checkForConcept(operand.getConcept(), myConcept);

    if (method.isVirtual()) {
      return invokeVirtual(operand, method, parameters);
    } else {
      return invokeNonVirtual(operand, method, parameters);
    }
  }

  @Override
  public final <T> T invoke(@NotNull SAbstractConcept operand, @NotNull SMethod<T> method, Object... parameters) {
    checkDescriptorIsInitialized();
    checkStatic(method);
    checkForConcept(operand, myConcept);

    if (method.isVirtual()) {
      return invokeVirtual(operand, method, parameters);
    } else {
      return invokeNonVirtual(operand, method, parameters);
    }
  }

  @Override
  public final <T> T invokeSuper(@NotNull SNode operand, @NotNull SMethod<T> method, Object... parameters) {
    checkDescriptorIsInitialized();
    checkNotStatic(method);
    checkForConcept(operand.getConcept(), myConcept);
    assert method.isVirtual();

    return invokeVirtualSuper(operand, method, parameters);
  }

  @Override
  public final <T> T invokeSuper(@NotNull SAbstractConcept operand, @NotNull SMethod<T> method, Object... parameters) {
    checkDescriptorIsInitialized();
    checkStatic(method);
    checkForConcept(operand, myConcept);
    assert method.isVirtual();

    return invokeVirtualSuper(operand, method, parameters);
  }

  private <T> T invokeNonVirtual(@NotNull SNode node, @NotNull SMethod<T> method, Object... parameters) {
    checkNotStatic(method);
    return invokeNonVirtualCommon(NodeOrConcept.create(node), method, parameters);
  }

  private <T> T invokeNonVirtual(@NotNull SAbstractConcept concept, @NotNull SMethod<T> method, Object... parameters) {
    checkStatic(method);
    return invokeNonVirtualCommon(NodeOrConcept.create(concept), method, parameters);
  }

  private <T> T invokeNonVirtualCommon(@NotNull NodeOrConcept nodeOrConcept, @NotNull SMethod<T> method, Object... parameters) {
    checkDescriptorIsInitialized();
    checkForConcept(nodeOrConcept.getConcept(), myConcept);

    if (method.getModifiers().isPrivate()) {
      if (nodeOrConcept.getNode() != null) {
        return invokeSpecial(nodeOrConcept.getNode(), method, parameters);
      } else {
        return invokeSpecial(nodeOrConcept.getConcept(), method, parameters);
      }
    }
    Iterable<SAbstractConcept> ancestors = myAncestorCache.getAncestorsInvocationOrder();
    for (SAbstractConcept ancestor : ancestors) {
      BHDescriptor descriptor = getBHDescriptor(ancestor);
      if (hasDeclaredMethod(descriptor, method)) {
        if (nodeOrConcept.getNode() != null) {
          return descriptor.invokeSpecial(nodeOrConcept.getNode(), method, parameters);
        } else {
          return descriptor.invokeSpecial(nodeOrConcept.getConcept(), method, parameters);
        }
      }
    }
    throw new BHMethodNotFoundException(this, method);
  }

  private <T> T invokeVirtual(@NotNull SNode operand, @NotNull SMethod<T> method, Object... parameters) {
    BHDescriptor descriptor = findDescriptorByVirtualMethod(method, false);
    return descriptor.invokeSpecial(operand, method, parameters);
  }

  private <T> T invokeVirtual(@NotNull SAbstractConcept operand, @NotNull SMethod<T> method, Object... parameters) {
    BHDescriptor descriptor = findDescriptorByVirtualMethod(method, false);
    return descriptor.invokeSpecial(operand, method, parameters);
  }

  private <T> T invokeVirtualSuper(SNode operand, SMethod<T> method, Object... parameters) {
    BHDescriptor descriptor = findDescriptorByVirtualMethod(method, true);
    return descriptor.invokeSpecial(operand, method, parameters);
  }

  private <T> T invokeVirtualSuper(SAbstractConcept operand, SMethod<T> method, Object... parameters) {
    BHDescriptor descriptor = findDescriptorByVirtualMethod(method, true);
    return descriptor.invokeSpecial(operand, method, parameters);
  }

  @NotNull
  private <T> BHDescriptor findDescriptorByVirtualMethod(SMethod<T> method, boolean superOnly) {
    assert method.isVirtual();
    SMethod<?> methodImplementation = superOnly ? mySuperVTable.get(method.getId())
                                                : myVTable.get(method.getId());
    if (methodImplementation == null) {
      throw new BHMethodIsNotFoundInVTable(this, method);
    }
    if (methodImplementation.isAbstract()) {
      throw new BHMethodImplementationIsNotFoundException(this, method);
    }
    return getBHDescriptor(methodImplementation.getConcept());
  }

  @Override
  public <T> T invokeSpecial(@NotNull SNode operand, @NotNull SMethod<T> method, Object... parameters) {
    checkDescriptorIsInitialized();
    checkNotStatic(method);
    checkForConcept(operand.getConcept(), myConcept);
    @Nullable Object[] parametersArray = getParametersArray(method.getParameters(), parameters);
    if (parametersArray != null) {
      checkParameters(this, method, parametersArray);
    }
    return invokeSpecial0(operand, method, parametersArray);
  }

  @Override
  public <T> T invokeSpecial(@NotNull SAbstractConcept operand, @NotNull SMethod<T> method, Object... parameters) {
    checkDescriptorIsInitialized();
    checkStatic(method);
    checkForConcept(operand, myConcept);
    @Nullable Object[] parametersArray = getParametersArray(method.getParameters(), parameters);
    if (parametersArray != null) {
      checkParameters(this, method, parametersArray);
    }
    return invokeSpecial0(operand, method, parametersArray);
  }

  @NotNull
  @Override
  public List<SMethod<?>> getMethods() {
    List<SMethod<?>> currentMethods = myCachedMethods.get();
    if (currentMethods == null) {
      Set<SMethod<?>> result = new HashSet<>();
      for (SAbstractConcept concept : myAncestorCache.getAncestorsConstructionOrder()) {
        BHDescriptor bhDescriptor = getBHDescriptor(concept);
        List<SMethod<?>> conceptMethods = bhDescriptor.getDeclaredMethods();
        for (SMethod<?> method : conceptMethods) {
          if (method.getModifiers().isPublic() && !method.getModifiers().isVirtual()) {
            result.add(method);
          }
        }
      }
      result.addAll(myVTable.getMethods());
      currentMethods = new ArrayList<>(result);
      if (myCachedMethods.compareAndSet(null, currentMethods)) {
        return currentMethods;
      } else {
        return myCachedMethods.get();
      }
    }
    return currentMethods;
  }

  /**
   * @generated : listing all the declared methods
   * NB: must be fast
   **/
  @NotNull
  @Override
  public abstract List<SMethod<?>> getDeclaredMethods();

  /**
   * @param node        -- the new node to initialize
   * @param constructor -- constructor to invoke
   * @param parameters  -- parameters to pass to the constructor
   * @generated : switch by constructor; invoking without calling supers
   */
  protected abstract void initNode(@NotNull SNode node, @NotNull SConstructor constructor, @Nullable Object[] parameters);

  /**
   * invokes a method without dynamic resolution
   *
   * @param parameters is an array of arguments.
   *                   NB: in the case of the last var arg parameter, the last array member is actually packed into another array
   * @throws BHMethodNotFoundException if the method has not been found
   * @generated : switch by the method; direct invocation in each case
   **/
  protected abstract <T> T invokeSpecial0(@NotNull SNode node, @NotNull SMethod<T> method, @Nullable Object[] parameters);

  /**
   * invokes a static method without dynamic resolution
   *
   * @throws BHMethodNotFoundException if the method has not been found
   * @generated : switch by the method; direct invocation in each case
   **/
  protected abstract <T> T invokeSpecial0(@NotNull SAbstractConcept concept, @NotNull SMethod<T> method, @Nullable Object[] parameters);

  /**
   * @return true iff the method exists (constructor is not a method here)
   **/
  private static <T> boolean hasDeclaredMethod(BHDescriptor descriptor, @NotNull SMethod<T> method) {
    return descriptor.getDeclaredMethods().contains(method);
  }

  @Override
  public String toString() {
    return getConcept() + " BHDescriptor";
  }

  private final class ConstructionHandler {
    private final AncestorCache myAncestorCache;
    private final SAbstractConcept myConcept;

    public ConstructionHandler(AncestorCache ancestorCache, SAbstractConcept concept) {
      myAncestorCache = ancestorCache;
      myConcept = concept;
    }

    public void initNode(@NotNull SNode node, @NotNull SConstructor constructor, @Nullable Object[] parameters) {
      //Qualified name is used just because we have instances of interfaces, and instance.getConcept() returns SConcept.
      //This should be considered a hack and removed when possible
      assert myConcept.getQualifiedName().equals(node.getConcept().getQualifiedName()) : "myConcept=" + myConcept + "; node.concept=" + node.getConcept();
      for (SAbstractConcept ancestor : myAncestorCache.getAncestorsConstructionOrder()) {
        BHDescriptor ancestorDescriptor = BaseBHDescriptor.this.getBHDescriptor(ancestor);
        if (ancestorDescriptor instanceof BaseBHDescriptor) {
          ((BaseBHDescriptor) ancestorDescriptor).initNode(node, constructor, parameters);
        }
      }
    }
  }

  public static final class BHNotInitializedException extends RuntimeException {
    public BHNotInitializedException(SAbstractConcept concept) {
      super("Behavior descriptor has not been initialized; concept :  " + concept);
    }
  }
}

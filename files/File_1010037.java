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
package jetbrains.mps.smodel.behaviour;

import jetbrains.mps.core.aspects.behaviour.api.BHDescriptor;
import jetbrains.mps.core.aspects.behaviour.api.BehaviorRegistry;
import jetbrains.mps.core.aspects.behaviour.api.SConstructor;
import jetbrains.mps.core.aspects.behaviour.api.SMethod;
import jetbrains.mps.core.aspects.behaviour.api.SMethodId;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Internal;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

/**
 * Behavior Reflection Facade.
 * API for the generated behavior code.
 * The API is null-safe
 */
@SuppressWarnings("ALL")
public final class BHReflection {
  private static BehaviorRegistry ourRegistry;

  @NotNull
  public static SNode newNode(@Nullable SModel model, @NotNull SConstructor constructor, Object... parameters) {
    return constructor.newNode(model, parameters);
  }

  public static void initNode(@NotNull SNode node) {
    BHDescriptor bhDescriptor = getBHDescriptor(node.getConcept());
    bhDescriptor.initNode(node);
  }

  /**
   * @deprecated operand is not null actually, use #invoke0 below instead
   */
  @Deprecated
  @ToRemove(version = 2018.1)
  public static Object invoke(@Nullable SNode operand, @NotNull SMethodId methodId, Object... parameters) {
    return invoke0(operand, operand.getConcept(), methodId, parameters);
  }

  /**
   * @deprecated operand is not null actually, use #invoke0 below instead
   */
  @Deprecated
  @ToRemove(version = 2018.1)
  public static Object invoke(@Nullable SAbstractConcept operand, @NotNull SMethodId methodId, Object... parameters) {
    return invoke0(operand, operand, methodId, parameters);
  }

  /**
   * NB: Here we call #invoke and not #invokeSpecial since the method could be a non-virtual invocation from one of the ancestors
   * Separating #invoke in two #invokeNonVirtual and #invokeVirtual instructions we can get rid of
   */
  public static Object invoke0(@Nullable SNode operand, @NotNull SAbstractConcept concept, @NotNull SMethodId methodId, Object... parameters) {
    BHDescriptor bhDescriptor = getBHDescriptor(concept);
    SMethod<?> method = findMethodByReflection(methodId, bhDescriptor);
    return method.invoke(operand, parameters);
  }

  public static Object invoke0(@Nullable SAbstractConcept operand, @NotNull SAbstractConcept concept, @NotNull SMethodId methodId, Object... parameters) {
    BHDescriptor bhDescriptor = getBHDescriptor(concept);
    SMethod<?> method = findMethodByReflection(methodId, bhDescriptor);
    return method.invoke(operand, parameters);
  }

  /**
   * @return the correct method for Id (for virtual methods we look for a right implementation concept as well)
   */
  @NotNull
  private static SMethod<?> findMethodByReflection(@NotNull SMethodId methodId, BHDescriptor bhDescriptor) {
    SMethod<?> method = bhDescriptor.getMethod(methodId);
    if (method == null) {
      throw new BHNoSuchMethodException(methodId, bhDescriptor);
    }
    return method;
  }

  /**
   * invokes a method specifically in the concreteConcept behavior.
   */
  public static Object invokeSpecial(@Nullable SNode operand, @NotNull SAbstractConcept concreteConcept, @NotNull SMethodId methodId, Object... parameters) {
    BHDescriptor bhDescriptor = getBHDescriptor(concreteConcept);
    SMethod<?> method = findMethodByReflection(methodId, bhDescriptor);
    return method.invokeSpecial(operand, parameters);
  }

  public static Object invokeSpecial(@Nullable SAbstractConcept operand, @NotNull SAbstractConcept concreteConcept, @NotNull SMethodId methodId, Object... parameters) {
    BHDescriptor bhDescriptor = getBHDescriptor(concreteConcept);
    SMethod<?> method = findMethodByReflection(methodId, bhDescriptor);
    return method.invokeSpecial(operand, parameters);
  }

  /**
   * method has to be virtual
   * invokes method implementation which is strictly after the given concrete concept in the ancestor linearization of the node's concept
   */
  public static Object invokeSuper(@Nullable SNode operand, @NotNull SAbstractConcept concreteConcept, @NotNull SMethodId methodId, Object... parameters) {
    BHDescriptor bhDescriptor = getBHDescriptor(concreteConcept);
    SMethod<?> method = findMethodByReflection(methodId, bhDescriptor);
    return method.invokeSuper(operand, concreteConcept, parameters);
  }

  public static Object invokeSuper(@Nullable SAbstractConcept operand, @NotNull SAbstractConcept concreteConcept, @NotNull SMethodId methodId, Object... parameters) {
    if (operand == null) {
      return null;
    }
    BHDescriptor bhDescriptor = getBHDescriptor(concreteConcept);
    SMethod<?> method = findMethodByReflection(methodId, bhDescriptor);
    return method.invokeSuper(operand, concreteConcept, parameters);
  }

  @NotNull
  private static BHDescriptor getBHDescriptor(@NotNull SAbstractConcept concept) {
    return ourRegistry.getBHDescriptor(concept);
  }

  @Internal
  /*package*/ static void setRegistry(@NotNull BehaviorRegistry registry) {
    ourRegistry = registry;
  }

  /**
   * We have it extending the RuntimeException opposed to java {@link java.lang.NoSuchMethodException}
   */
  private static class BHNoSuchMethodException extends RuntimeException {
    public BHNoSuchMethodException(@NotNull SMethodId methodId, BHDescriptor descriptor) {
      super("SMethod with id '" + methodId + "' could not be found within the " + descriptor + " behavior descriptor");
    }
  }
}

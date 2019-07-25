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
import jetbrains.mps.core.aspects.behaviour.api.SConstructor;
import jetbrains.mps.core.aspects.behaviour.api.SMethod;
import jetbrains.mps.core.aspects.behaviour.api.SMethodId;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collections;
import java.util.List;

public final class IllegalBHDescriptor implements BHDescriptor {
  private static final Logger LOG = LogManager.getLogger(IllegalBHDescriptor.class);

  private final SAbstractConcept myConcept;

  public IllegalBHDescriptor(@NotNull SAbstractConcept concept) {
    myConcept = concept;
  }

  @Override
  public void initNode(@NotNull SNode node) {
    reportWarn();
  }

  @NotNull
  @Override
  public SNode newNode(@Nullable SModel model, @NotNull SConstructor constructor, Object... parameters) {
    throwException();
    return null;
  }

  @Override
  public <T> T invoke(@NotNull SNode operand, @NotNull SMethod<T> method, Object... parameters) {
    throwException();
    return null;
  }

  @Override
  public <T> T invoke(@NotNull SAbstractConcept operand, @NotNull SMethod<T> method, Object... parameters) {
    throwException();
    return null;
  }

  @Override
  public <T> T invokeSuper(@NotNull SNode operand, @NotNull SMethod<T> method, Object... parameters) {
    throwException();
    return null;
  }

  @Override
  public <T> T invokeSuper(@NotNull SAbstractConcept operand, @NotNull SMethod<T> method, Object... parameters) {
    throwException();
    return null;
  }

  @Override
  public <T> T invokeSpecial(@NotNull SNode operand, @NotNull SMethod<T> method, Object... parameters) {
    throwException();
    return null;
  }

  @Override
  public <T> T invokeSpecial(@NotNull SAbstractConcept operand, @NotNull SMethod<T> method, Object... parameters) {
    throwException();
    return null;
  }

  @NotNull
  @Override
  public List<SMethod<?>> getMethods() {
    reportWarn();
    return Collections.emptyList();
  }

  @NotNull
  @Override
  public List<SMethod<?>> getDeclaredMethods() {
    reportWarn();
    return Collections.emptyList();
  }

  @Nullable
  @Override
  public SMethod<?> getMethod(@NotNull SMethodId methodId) {
    reportWarn();
    return null;
  }

  @NotNull
  @Override
  public SAbstractConcept getConcept() {
    return myConcept;
  }

  private void reportWarn() {
    LOG.warn("IllegalBehaviorDescriptor was created for the concept " + myConcept + " which operates null-safe in this context.");
  }

  private void throwException() {
    throw new IllegalBehaviorException(myConcept);
  }

  @Override
  public String toString() {
    return "IllegalBHDescriptor[" + myConcept + "]";
  }

  private static class IllegalBehaviorException extends RuntimeException {
    public IllegalBehaviorException(SAbstractConcept concept) {
      super("IllegalBehaviorDescriptor was created for the concept " + concept + " and which does not support the requested operation.");
    }
  }
}

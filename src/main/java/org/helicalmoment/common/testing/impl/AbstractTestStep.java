/*
 * Copyright 2012 Mike Melton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.helicalmoment.common.testing.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.helicalmoment.common.testing.TestParameters;
import org.helicalmoment.common.testing.TestStep;

/**
 * A default implementation of {@link TestStep} which provides getters for the name
 * and description.
 * @param <T> the step's {@link TestParameters} implementation
 */
@Getter @RequiredArgsConstructor @EqualsAndHashCode
public abstract class AbstractTestStep<T extends TestParameters> implements TestStep<T> {
	@NonNull private final String name;
	@NonNull private final String description; 
}

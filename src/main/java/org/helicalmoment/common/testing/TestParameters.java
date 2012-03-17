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
package org.helicalmoment.common.testing;

/**
 * Marker interface for test parameters. Implementations will provide methods to
 * {@link TestStep}s to indirectly access to data within a {@link TestCase}.
 * <p/>
 * TODO: Consider renaming either this or the entire {@link ParametersStrategy}
 * hierarchy. Both refer to "parameters" but they mean completely different
 * things (i.e., this interface refers to data a {@link TestCase} should expose
 * but {@code ParametersStrategy} refers to the inputs to a parameterized test
 * (i.e., {@code List<TestCase>}).
 */
public interface TestParameters {
}

/*
 * Created on Dec 20, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.data.Index.atIndex;
import static org.fest.assertions.error.ShouldNotContainAtIndex.shouldNotContainAtIndex;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.FloatArrayFactory.*;
import static org.fest.assertions.test.TestData.*;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.mockito.Mockito.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.data.Index;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link FloatArrays#assertDoesNotContain(AssertionInfo, float[], float, Index)}</code>.
 *
 * @author Alex Ruiz
 */
public class FloatArrays_assertDoesNotContain_at_Index_Test {

  private static float[] actual;

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private FloatArrays arrays;

  @BeforeClass public static void setUpOnce() {
    actual = array(6f, 8f, 10f);
  }

  @Before public void setUp() {
    failures = spy(new Failures());
    arrays = new FloatArrays();
    arrays.failures = failures;
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    arrays.assertDoesNotContain(someInfo(), null, 8f, someIndex());
  }

  @Test public void should_pass_if_actual_does_not_contain_value_at_Index() {
    arrays.assertDoesNotContain(someInfo(), actual, 6f, atIndex(1));
  }

  @Test public void should_pass_if_actual_is_empty() {
    arrays.assertDoesNotContain(someInfo(), emptyArray(), 8f, someIndex());
  }

  @Test public void should_throw_error_if_Index_is_null() {
    thrown.expectNullPointerException("Index should not be null");
    arrays.assertDoesNotContain(someInfo(), actual, 8f, null);
  }

  @Test public void should_pass_if_Index_is_out_of_bounds() {
    arrays.assertDoesNotContain(someInfo(), actual, 8f, atIndex(6));
  }

  @Test public void should_fail_if_actual_contains_value_at_index() {
    AssertionInfo info = someInfo();
    Index index = atIndex(0);
    try {
      arrays.assertDoesNotContain(info, actual, 6f, index);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldNotContainAtIndex(actual, 6f, index));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}

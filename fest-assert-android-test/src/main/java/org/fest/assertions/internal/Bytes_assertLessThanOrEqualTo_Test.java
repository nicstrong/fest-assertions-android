/*
 * Created on Oct 21, 2010
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

import static org.fest.assertions.error.ShouldBeLessOrEqual.shouldBeLessOrEqual;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.mockito.Mockito.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link Bytes#assertLessThanOrEqualTo(AssertionInfo, Byte, byte)}</code>.
 *
 * @author Alex Ruiz
 */
public class Bytes_assertLessThanOrEqualTo_Test {

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private Bytes bytes;

  @Before public void setUp() {
    failures = spy(new Failures());
    bytes = new Bytes();
    bytes.failures = failures;
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    bytes.assertLessThanOrEqualTo(someInfo(), null, (byte)8);
  }

  @Test public void should_pass_if_actual_is_less_than_other() {
    bytes.assertLessThanOrEqualTo(someInfo(), (byte)6, (byte)8);
  }

  @Test public void should_pass_if_actual_is_equal_to_other() {
    bytes.assertLessThanOrEqualTo(someInfo(), (byte)6, (byte)6);
  }

  @Test public void should_fail_if_actual_is_greater_than_other() {
    AssertionInfo info = someInfo();
    try {
      bytes.assertLessThanOrEqualTo(info, (byte)8, (byte)6);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeLessOrEqual((byte)8, (byte)6));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
